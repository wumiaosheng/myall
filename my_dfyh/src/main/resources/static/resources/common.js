(function($) {
	
	$.fn.disable = function() {
		this.each(function(i) {
			$(this).find(':input,:checkbox,:radio,button').add(this).each(function() {
				var id = this.id, t = $(this);
				/*
				 * if (keyObj[id]) $.hotkeys.remove(keyObj[id]);
				 */
				if(t.data("_switcher"))
					t.data("_switcher").disable();
				switch (this.tagName.toLowerCase()) {
				case 'button':
				case 'textarea':
				case 'select':
				case 'input':
					this.disabled = true;
					if (t.data("suggest_raw")) {
						t.data("suggest_raw").disable();
					}
					break;
				}
				if(t.hasClass('selectpicker'))
					t.selectpicker('refresh');
			});
		});
	};
	$.fn.enable = function() {
		this.each(function(i) {
			$(this).find(':input,:checkbox,:radio,button').add(this).each(function() {
				var id = this.id, t = $(this);
				if(t.data("_switcher"))
					t.data("_switcher").enable();
				switch (this.tagName.toLowerCase()) {
				case 'button':
				case 'textarea':
				case 'select':
				case 'input':
					if (t.data("suggest_raw")) {
						t.data("suggest_raw").enable();
					}
					this.disabled = null;
					break;
				}
				if(t.hasClass('selectpicker'))
					t.selectpicker('refresh');
			});
		});
	};
	var attrObjs = [];
	$.fn.attrObj = function(name, value) {
		var obj;
		for (var i = 0; i < attrObjs.length; i++) {
			if (attrObjs[i].o == this[0]) {
				obj = attrObjs[i];
				break;
			}
		}
		if (!obj) {
			obj = new Object();
			obj.o = this[0];
			obj.v = new Object();
			attrObjs.push(obj);
		}
		if (value) {
			obj.v[name] = value;
		} else {
			return obj.v[name];
		}
	};
	$.fn.removeAttrObj = function(name) {
		for (var i = 0; i < attrObjs.length; i++) {
			if (attrObjs[i].o == this[0]) {
				var obj = attrObjs[i];
				obj.v[name] = null;
				break;
			}
		}
	};
	$.fn.grid = function(options) {
		var $t = this,id=$t.attr("id"),pid =id+ "_pager";
		var settings = {
			datatype : "json",
			height : 350,
			mtype : "POST",
			autowidth : true,
			autoCode : true,
			rowNum : 10,
			rowList : [ 10, 20, 30, 50, 100 ],
			jsonReader : {
				root : "data",
				page : "page",
				total : "pages",
				records : "total"
			},
			gridview : false,
			afterInsertRow : function(rowid, rowdata, rawdata) {
				$("#" + rowid, $(this)).data("rawData", rawdata);
			},
			gridComplete: function(){
			    ////设置单选
				var multiboxonly=options.multiboxonly;
				if(multiboxonly){
					 $("#cb_"+id).hide();  
					 return (true); 
				}
			},
			viewrecords : true
		};

		var genPager = options.noPager ? false : true;
		if (genPager) {
			var $p = $("<div></div>");
			$p.attr("id", pid);
			$t.after($p);
			settings.pager = "#" + pid;
		}
		options = $.extend(settings, options);
		if (options.autoCode) {
			options.gridComplete = function(id) {
				$t.codetext();
				$t.codeoption();
			};
		}

		if (!options.colMenu)
			options.colMenu = options.menu;

		var cm = options.colModel, cns = options.colNames;
		if (!cns)
			options.colNames = [];
		for (var i = 0; i < cm.length; i++) {
			var cmi = cm[i];
			if (cmi.codecfg) {
				cmi.classes = "codecfg";
				cmi.cellattr = function(ts, rowId, tv, rawObject, cm, rdata) {
					return "codecfg=\"" + rawObject.codecfg.replace(/"/g, "'")
							+ "\"";
				}
			}
			if (!cmi.coloptions) {
				cmi.coloptions = {
					sorting : true,
					columns : true,
					filtering : false,
					seraching : false,
					grouping : false,
					freeze : false
				};
			}
			if (!cns) {
				options.colNames.push(cmi.name);
				cmi.name = cmi.index;
			}
		}

		if (options.tableCfg && options.tableCfg.length > 0) {
			var tcfgs = options.tableCfg;
			// 隐藏：hidden : true,
			// 宽度：width
			// 顺序
			var cn = options.colNames;
			var ncm = [], ncn = [];
			for (var i = 0; i < tcfgs.length; i++) {
				var tcfg = tcfgs[i];
				for (var j = 0; j < cm.length; j++) {
					var m = cm[j];
					if (m.index == tcfg.id) {
						if (tcfg.display == 'none')
							m.hidden = true;
						else
							m.hidden = false;
						if (m.width < tcfg.width)
							m.width = tcfg.width;
						ncm.push(m);
						ncn.push(cn[j]);
					}
				}
			}
			// 新加的列显示到最后
			for (var j = 0; j < cm.length; j++) {
				var m = cm[j], flag = false;
				for (var i = 0; i < ncm.length; i++) {
					var nc = ncm[i];
					if (nc.index == m.index) {
						flag = true; // 已存在
						break;
					}
				}
				if (!flag) { // 不存在
					ncm.push(m);
					ncn.push(cn[j]);
				}
			}
			options.colModel = ncm;
			cm = options.colModel;
			options.colNames = ncn;
		}

		$t.attr("isGrid", true);
		$t.jqGrid(options);

		if (options.searchbar) {
			$t.jqGrid('filterToolbar');
		}

		$(window).bind("resize", function() {
			var width =$(".jqGrid_wrapper").width();
			$t.setGridWidth(width);
		})
	};
	$.fn.reloadGrid = function(options) {
		var settings = {
			page : 1
		};
		options = $.extend(settings, options);
		if (!options.postData) {
			options.postData = options.data;
		}
		this.jqGrid("setGridParam", options).trigger("reloadGrid");
	};
	
})(jQuery);