
/**
 * 四舍六入五成双
 * @param {*} value 
 */
export default function math (value) {

    if(value==undefined||value==null||value<=0){

        return '—'
    }
    value=value+""
    if(value.indexOf(".")<0){
        return value
    }
    
    let s=value.split(".")
    let i=s[0]
    let m=s[1].substring(0,1)
    if(parseInt(m)>5){
        return parseInt(i)+1
    }
    if(parseInt(m)<5){
        return parseInt(i)
    }
    if(parseInt(m)==5){
        if(s[1].length==1){
            return parseInt(i)+1
        }
        let n=s[1].substring(1,2)
        if(n%2==0){
            return parseInt(i)+1
        }
        return parseInt(i)
    }
}