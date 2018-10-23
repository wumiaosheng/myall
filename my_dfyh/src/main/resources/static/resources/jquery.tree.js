(function($) {

	$.fn.tree = function(options) {
		var settings = {
			selectedMulti : false,
			checkEnable : false
		};
		options = $.extend(settings, options);

		var setting = {
			async : {
				enable : false,
				autoParam : [ "id" ],
				contentType : "application/json"
			},
			edit : {
				enable : false,
				showRemoveBtn : false,
				showRenameBtn : false
			},
			callback : {},
			check : {
				enable : options.checkEnable
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : 0
				},
				key : {
					name : "name"
				}
			},
			view : {
				selectedMulti : options.selectedMulti,
				fontCss: $.getFontCss
			}
		}, oe = setting.edit, oc = setting.callback, od = setting.data, ods = od.simpleData, odk = od.key;

		if (options.onClick)
			oc.onClick = options.onClick;
		if (options.onDblClick)
			oc.onDblClick = options.onDblClick;
		if (options.beforeDrag)
			oc.beforeDrag = options.beforeDrag;
		if (options.beforeDrop)
			oc.beforeDrop = options.beforeDrop;
		if (options.onAsyncSuccess)
			oc.onAsyncSuccess = options.onAsyncSuccess;
		if (options.onAsyncError)
			oc.onAsyncError = options.onAsyncError;
		if (options.beforeAsync)
			oc.beforeAsync = options.beforeAsync;
		if (options.idKey)
			ods.idKey = options.idKey;
		if (options.pIdKey)
			ods.pIdKey = options.pIdKey;
		if (options.rootPId)
			ods.rootPId = options.rootPId;
		if (options.nameKey)
			odk.name = options.nameKey;
		if (options.enableEdit)
			oe.enable = true;
		if (options.enableRename)
			oe.showRenameBtn = true;
		if (options.enableRemove)
			oe.showRemoveBtn = true;
		if (options.enableCheck)
			setting.check.enable = true;
		if (options.ajaxUrl) {
			setting.async.enable = true;
			setting.async.autoParam = [ ods.idKey ];
			if (options.asyncParam)
				setting.async.otherParam = options.asyncParam;
		}
		if (options.addHoverDom) {
			setting.view.addHoverDom = options.addHoverDom
		}
		if (options.removeHoverDom) {
			setting.view.removeHoverDom = options.removeHoverDom
		}
		if (options.dblClickExpand) {
			setting.view.dblClickExpand = options.dblClickExpand
		}
		if (options.render) {// set nameIsHTML to true while render
			// customized
			setting.view.nameIsHTML = true;
			setting.render = options.render;
		}
		if (options.nodeHandler) {
			setting.nodeHandler = options.nodeHandler;
		}
		if (options.ajaxUrl) {
			setting.ajaxUrl = options.ajaxUrl;
		}
		if (options.title) {
			odk.title = options.title;
		}

		// update the name value of data while renamed
		oc.onRename = function(evt, tid, tNode) {
			tNode.data[odk.name] = tNode[odk.name];
		};

		var t = this[0], id = t.id, data = [];
		// t.className = "ztree";
		$(this).replaceWith("<ul id='" + id + "' class='ztree'></ul>");
		t = $('#' + id)
		var url=options.url;
		var param=options.param;
		if(!param){///没有传递参数
			param={};
		}
		if(url){////自定义获取
			$.ajax({
				  type:"post",
				  url: url,
				  data:param,
				  dataType:"json",
				  async: false,
				  success: function(d){
					  data=d; 
               }
		   });
		}else if ($._autofill) {
			var $af = $._autofill, $afg = $af.filllists;
			if ($afg && $afg[id])
				data = $afg[id];
		}
		$.fn.zTree.init(t, setting, data);
	};

	$.fn.treeObj = function() {
		var id = this[0].id;
		return $.fn.zTree.getZTreeObj(id);
	};
	
	$.getFontCss=function(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
})(jQuery);
