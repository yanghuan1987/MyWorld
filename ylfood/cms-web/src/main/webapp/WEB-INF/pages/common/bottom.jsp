<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<% String path = application.getInitParameter ("static-file"); %>
<div id='popUp' class='sp-popup sp-popup-normal'>
	<div class='sp-pop-header'>
		<div id="pop-logo" class="sp-pop-logo">
			<span>弹出框标题</span>
		</div>
		<div class="sp-pop-exit">
			<a href="javascript:exitPop('popUp');"><img
				src='<%=path%>/img/cross.png'></a>
		</div>
	</div>
	<div class='sp-pop-content'>
		<input type='button' value='提交' class='sp-btn sp-btn-gray-sm'
			onclick="javascript:exitPop('popUp');" />
	</div>
</div>
<!---弹出框-普通---->
  <div id='popUp'class='sp-popup sp-popup-normal'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>弹出框标题</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popUp');"><img src='<%=path%>/img/cross.png'></a></div>
	</div>
	<div class='sp-pop-content-normal'>
	  <input type='button' value='提交' class='sp-btn sp-btn-gray-sm' onclick="javascript:exitPop('popUp');"/>
	</div>
  </div>
<!---弹出框-大---->
  <div id='popUpLg'class='sp-popup sp-popup-lg'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>弹出框标题</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popUpLg');"><img src='<%=path%>/img/cross.png'></a></div>
	</div>
	<div class='sp-pop-content-lg'>
	  
	</div>
  </div>

  <!---弹出框-超级大---->
  <div id='popUpHg'class='sp-popup sp-popup-hg'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>弹出框标题</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popUpHg');"><img src='<%=path%>/img/cross.png'></a></div>
	</div>
	<div class='sp-pop-content-lg'>
	  
	</div>
  </div>


  <!--提示框-->
   <div id='popHint'class='sp-popup sp-popup-sm'>
    
	<div class='sp-pop-content'>
	  <span>操作成功/操作失败</span>
	</div>
  </div>
  <!---弹出框-超级大---->
  <div id='popDialogue'class='sp-popup sp-popup-md'>
    <div class='sp-pop-header'>
    <div id="pop-logo" class="sp-pop-logo"><span>对话弹出框</span></div>
	<div class="sp-pop-exit"><a href="javascript:exitPop('popDialogue');"><img src='<%=path%>/img/cross.png'></a></div>
	</div>
	<div class='sp-pop-content'>
	  
	</div>
  </div>

<script type="text/javascript"
	src="<%=path%>/js/lib/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/js/popup.js"></script>