package com.wang.test;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		File file = new File("wang.txt");
		System.out.println(file.getFreeSpace());
		System.out.println(file.getName());
	}


//	//成人、儿童 + 操作——代码重构
//	/**
//	 * 功能:成人、儿童 + 操作——代码重构
//	 * 参数： that  当前点击操作的Node,对应页面中的加号
//	 *
//	 * */
//	function addPeopleOperator(that) {
//		var that = $(this);						//当前元素Node
//		var smallReduce = that.prev().prev().prev();			//用来设置取消 - 操作灰色显示
//		var maxNumber = parseInt(that.parent().parent().find(".currentMaxNumber .maxNumber").html());			//可入住人数
//		var childrenNumber = parseInt(that.parent().next().find(".small-operat-number").html());		//当前儿童数目
//		var originChildrenNumber = parseInt(that.parent().next().find("#originChildNum").html());		//原订单儿童数目
//		var smallOperatNumber =$(this).prev();
//		var number = parseInt(smallOperatNumber.text());			//成人数目
//		if((number+childrenNumber) >= maxNumber){
//	    	cTzAlert("该房型已达到最大入住人数");
//	    	 return false;
//	    }
//		number++;
//		smallOperatNumber.text(number);
//		if(smallReduce.hasClass("small-reduce-zero-font")) {
//			smallReduce.removeClass("small-reduce-zero-font");
//		}
//		//变更旅客table中添加一条新增信息
//		var indexClass = that.parent().prev().prev().prev().attr("class");					//获取当前房间属于第几间
//		var index = parseInt(indexClass.substring(indexClass.indexOf("_") + 1));	//当前房间第几间-数字值
//		var alterCabinId = that.parent().parent().find(".currentMaxNumber .cabinId").text();
//		var alterRoomType = $.trim(that.parent().parent().find(".roomType .currentRoomType").text());
//		var originCabinName =alterRoomType;
//		var originCabinId=alterCabinId;
//		var cabinIndex = that.parent().parent().find(".roomType").attr("cabinIndex");
//		var price ="";						//售卖价格
//		var priceSettlement ="";	//结算价格
//		var alterPriceType = "";		//价格类型
//		var TFCPrice = that.parent().parent().find(".currentMaxNumber .cabinSaleRulePriceTFC").text();
//		var TFCPriceSettlement = that.parent().parent().find(".currentMaxNumber .cabinPriceTFCSettlement").text();
//
//		if((originChildrenNumber + number) == 2 ) {
//			price = that.parent().parent().find(".currentMaxNumber .cabinSaleRulePriceFS").text();
//			priceSettlement = that.parent().parent().find(".currentMaxNumber .cabinPriceFSSettlement").text();
//			alterPriceType ="FS";
//			$("#alterPassenger").find(".index_" + index+".CH" +".ADD").each(function(){			//更新儿童价格
//				$(this).parent().find(".price .priceShow").text(TFCPrice);
//				$(this).parent().find(".price .alterPriceType").text("TFC");
//				$(this).parent().find(".price .alterPrice").text(TFCPrice);
//				$(this).parent().find(".price .priceSettlement").text(TFCPriceSettlement);
//			});
//		}else if((originChildrenNumber + number) > 2) {
//			alterPriceType = "TFA";
//			price = that.parent().parent().find(".currentMaxNumber .cabinSaleRulePriceTFA").text();
//			priceSettlement = that.parent().parent().find(".currentMaxNumber .cabinPriceTFASettlement").text();
//			$("#alterPassenger").find(".index_" + index+".CH" +".ADD").each(function(){			//更新儿童价格
//				$(this).parent().find(".price .priceShow").text(TFCPrice);
//				$(this).parent().find(".price .alterPriceType").text("TFC");
//				$(this).parent().find(".price .alterPrice").text(TFCPrice);
//				$(this).parent().find(".price .priceSettlement").text(TFCPriceSettlement);
//			});
//		}
//		var template = kendo.template($("#add_man").html());
//		var data = {
//				indexClass:indexClass,index:index,adOrCh:"AD",operatorType:'ADD',
//				alterRoomType:alterRoomType,price:price,cabinIndex:cabinIndex,
//				alterCabinId:alterCabinId,priceSettlement:priceSettlement,alterPriceType:alterPriceType,originCabinId:originCabinId,
//				originCabinName:originCabinName
//				};
//		var tbody = $("#alterPassenger");
//		for( var i =index;i > 0; i-- ) {			//实现房间排序-在该第几间位置插入
//			var size = tbody.find(".index_" + i).size();
//			if(size > 0) {
//				var currentPosition =  tbody.find(".index_"+i).last().parent();
//				currentPosition.after(template(data));
//				break ;
//			}
//			if(i == 1) {
//				tbody.prepend(template(data));
//			}
//		}
//		//修改新增人数
//		var alterPeopleNumNode = that.parent().parent().find(".selectResult");
//		var alterPeopleNum = parseInt($.trim(alterPeopleNumNode.text()));
//		alterPeopleNumNode.text(++alterPeopleNum);
//		generatorPrefix();		//刷新封装乘客 到Controller的顺序
//		addDatepickerEvent();
//
//
//
//		————————————————————————————————————————————
//		var that = $(this);
//		var smallReduce = that.prev().prev().prev();			//用来设置取消 - 操作灰色显示
//		var maxNumber = parseInt(that.parent().parent().find(".currentMaxNumber .maxNumber").html());			//可入住人数
//		var adultNumber = parseInt(that.parent().prev().find(".small-operat-number").html());		//成人数目
//		var cabinIndex = that.parent().parent().find(".roomType").attr("cabinIndex");
//		var smallOperatNumber =$(this).prev();
//		var number = parseInt(smallOperatNumber.text());			//儿童数目
//		if((number+adultNumber) >= maxNumber){
//			cTzAlert("该房型已达到最大入住人数");
//			return false;
//		}
//		number++;
//		smallOperatNumber.text(number);
//		if(smallReduce.hasClass("small-reduce-zero-font")) {
//			smallReduce.removeClass("small-reduce-zero-font");
//		}
//		//变更旅客table中添加一条新增信息
//		var indexClass = that.parent().prev().prev().prev().prev().attr("class");		//获取当前房间属于第几间
//		var index = parseInt(indexClass.substring(indexClass.indexOf("_") + 1));
//		var alterCabinId = that.parent().parent().find(".currentMaxNumber .cabinId").text();
//		var alterRoomType = $.trim(that.parent().parent().find(".roomType .currentRoomType").text());
//		var originCabinName =alterRoomType;
//		var originCabinId=alterCabinId;
//		var price = "0";
//		var priceSettlement ="";
//		var alterPriceType = "";
//		if((adultNumber + number) == 2) {
//			price = that.parent().parent().find(".currentMaxNumber .cabinSaleRulePriceFS").text();
//			priceSettlement = that.parent().parent().find(".currentMaxNumber .cabinPriceFSSettlement").text();
//			alterPriceType ="FS";
//		}else if((adultNumber + number) > 2){
//			price = that.parent().parent().find(".currentMaxNumber .cabinSaleRulePriceTFC").text();
//			priceSettlement = that.parent().parent().find(".currentMaxNumber .cabinPriceTFCSettlement").text();
//			alterPriceType = "TFC";
//		}
//		var template = kendo.template($("#add_man").html());
//		var data = {
//				indexClass:indexClass,index:index,adOrCh:"CH",operatorType:'ADD',
//				alterRoomType:alterRoomType,price:price,cabinIndex:cabinIndex,
//				alterCabinId:alterCabinId,priceSettlement:priceSettlement,alterPriceType:alterPriceType,originCabinId:originCabinId,
//				originCabinName:originCabinName
//				};
//		var tbody = $("#alterPassenger");
//		for( var i =index;i > 0; i-- ) {			//实现房间排序
//			var size = tbody.find(".index_" + i).size();
//			if(size > 0) {
//				var currentPosition =  tbody.find(".index_"+i).last().parent();
//				currentPosition.after(template(data));
//				break ;
//			}
//			if(i == 1) {
//				tbody.prepend(template(data));
//			}
//		}
//		//修改新增人数
//		var alterPeopleNumNode = that.parent().parent().find(".selectResult");
//		var alterPeopleNum = parseInt($.trim(alterPeopleNumNode.text()));
//		alterPeopleNumNode.text(++alterPeopleNum);
//		generatorPrefix();
//		addDatepickerEvent();


//	}



}
