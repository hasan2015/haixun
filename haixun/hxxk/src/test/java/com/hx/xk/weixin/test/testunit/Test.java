package com.hx.xk.weixin.test.testunit;

public class Test {
	public String assessBPAdult(double sys, double dia){
		String re = "正常";
		String ae = "偏低";
		String be = "正常高值";
		String ce = "轻度高血压";
		String de = "中度高血压";
		String ee = "重度高血压";
		String fe = "不在血压等级范围内";
		String fin = null;//最后返回值
		if (sys>=180 || dia>=110 ){
			fin = ee;//重度高血压
		}
		else if ((sys>=160 && sys<180) || (dia>=100 && dia<110)){
			fin = de;//中度高血压
		}
		else if ((sys>=140 && sys<160) || (dia>=90 && dia<100)){
			fin = ce;//轻度高血压
		}
		else if (sys < 90 || dia < 60){
			fin = ae;//偏低
		}
		else if ((sys>=120 && sys<140) || (dia>=80 && dia<90)){
			fin = be;//正常高值
		}
		else if ((sys>=90 && sys<120) && (dia>=60 && dia<80) ){
			fin = re;//正常
		}
		else fin = fe;//不在血压等级范围内
		 
		return fin;
	}
	
	public static void main(String[] args) {
		Test test=new Test();
		System.out.println(test.assessBPAdult(0,100));
	}

}
