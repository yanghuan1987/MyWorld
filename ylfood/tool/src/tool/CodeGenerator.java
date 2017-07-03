package tool;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.mybatis.generator.FileUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class CodeGenerator {
	public static void main(String[] args) {
		try {
			
			Properties config = new Properties();
			config.load(CodeGenerator.class.getResourceAsStream("/config.properties"));
			//基础DIR
			String projectPath = config.getProperty("project.dir");
			String rootPath = projectPath + config.getProperty("result.dir");
			String dataPath = projectPath + config.getProperty("data.dir");
			String vmPath = projectPath + config.getProperty("vm.dir");
			String mybatisPath = projectPath + config.getProperty("mybatis.dir");
			
			File rootResultFile = new File(rootPath);
			if(rootResultFile.exists()) {
				FileUtils.DeleteAllSubFile(rootPath);
			}
			
			//生成Mybatis domain和mapping文件
			genDomainAndMapping(mybatisPath);
			
			//生成DAO，manager
			String filenameStr = config.getProperty("domainFileNames");
			genDaoAndManager(rootPath, dataPath, filenameStr);
			
			System.out.println("ALL Done....");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void genDaoAndManager(String rootPath, String dataPath, String filenameStr) throws Exception {
		Properties prop = new Properties();
		//prop.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "/vm");
		prop.put("file.resource.loader.class",
	        	"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		 prop.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
	        prop.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
	        prop.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
		VelocityEngine ve = new VelocityEngine(prop);
		ve.init();
		Template daoImplTpt = ve.getTemplate("daoImpl.vm", "UTF-8");
		Template daoIntfTpt = ve.getTemplate("daoIntf.vm", "UTF-8");
		
		Template managerImplTpt = ve.getTemplate("managerImpl.vm", "UTF-8");
		Template managerIntfTpt = ve.getTemplate("managerIntf.vm", "UTF-8");
		
		if(!StringUtils.isBlank(filenameStr)) {
			String[] filenames = filenameStr.split(",");
			for (String filename : filenames) {
				Properties dataProp = new Properties();
				InputStream inputStream = new FileInputStream(new File(dataPath+filename+".properties"));
				Reader reader = new InputStreamReader(inputStream, "UTF-8");
				dataProp.load(reader);
				VelocityContext ctx = new VelocityContext();
				for (Object key : dataProp.keySet()) {
					ctx.put((String)key, dataProp.getProperty((String)key));
				}
				ctx.put("createTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				String domainNameStr = dataProp.getProperty("domainName");
				String[] domainNames = domainNameStr.split(",");
				for (String domainName : domainNames) {
					if(!StringUtils.isBlank(domainName)) {
						ctx.put("domainName", domainName);
						merge(daoImplTpt, ctx, rootPath + dataProp.getProperty("result.dir")+"/dao/impl/"+domainName+"DaoImpl.java");
						merge(daoIntfTpt, ctx, rootPath + dataProp.getProperty("result.dir")+"/dao/"+domainName+"Dao.java");
						
						merge(managerImplTpt, ctx, rootPath + dataProp.getProperty("result.dir")+"/manager/impl/"+domainName+"ManagerImpl.java");
						merge(managerIntfTpt, ctx, rootPath + dataProp.getProperty("result.dir")+"/manager/"+domainName+"Manager.java");
						
						writeLine(domainName+" DAO and SERVICE 生成完成！");
					}
				}
			}
		}
	}
	
	public static void genDomainAndMapping(String mybatisPath) {
		if(!StringUtils.isBlank(mybatisPath)) {
			List<File> mybatisFiles = getFiles(mybatisPath, ".xml");
			for (File mybatisfile : mybatisFiles) {
				genByMybatis(mybatisfile);
			}
		}
	}
	
	private static void genByMybatis(File configurationFile) {
		List<String> warnings = new ArrayList<String>();
		try {
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configurationFile);

            DefaultShellCallback shellCallback = new DefaultShellCallback(true);

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, shellCallback, warnings);

            ProgressCallback progressCallback = null;

            myBatisGenerator.generate(progressCallback, new HashSet<String>(), new HashSet<String>());

        } catch (XMLParserException e) {
            writeLine(getString("Progress.3")); //$NON-NLS-1$
            writeLine();
            for (String error : e.getErrors()) {
                writeLine(error);
            }

            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (InvalidConfigurationException e) {
            writeLine(getString("Progress.16")); //$NON-NLS-1$
            for (String error : e.getErrors()) {
                writeLine(error);
            }
            return;
        } catch (InterruptedException e) {
            // ignore (will never happen with the DefaultShellCallback)
            ;
        }

        for (String warning : warnings) {
            writeLine(warning);
        }

        if (warnings.size() == 0) {
        	writeLine();
            writeLine(configurationFile.getName()+" "+getString("Progress.4")); //$NON-NLS-1$
        } else {
            writeLine();
            writeLine(configurationFile.getName()+" "+getString("Progress.5")); //$NON-NLS-1$
        }
	}
	
	private static void writeLine(String message) {
        System.out.println(message);
    }

    private static void writeLine() {
        System.out.println();
    }

    private static List<File> getFiles(String fileDir, final String subfix) {
    	List<File> files = new ArrayList<File>();
    	
    	File dir = new File(fileDir);
    	if(dir.exists() && dir.isDirectory()) {
    		File[] fileArr = dir.listFiles(new FilenameFilter() {
				
				@Override
				public boolean accept(File dir, String name) {
					return name.contains(subfix);
				}
			});
    		if(fileArr != null) {
    			for (File file : fileArr) {
    				files.add(file);
				}
    		}
    	}
    	
    	return files;
    }
    
	private static void merge(Template template, VelocityContext ctx, String path) {
		PrintWriter writer = null;
		try {
			File file = new File(path);
			if(!file.exists()) {
				File dir = new File(file.getParent());
				if(!dir.exists()) {
					dir.mkdirs();
				}
				file.createNewFile();
			}
			writer = new PrintWriter(file);
			template.merge(ctx, writer);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}

}
