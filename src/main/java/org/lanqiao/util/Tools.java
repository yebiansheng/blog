package org.lanqiao.util;

import java.util.Properties;

public class Tools {
	/**
	 * ��ȡ�����ļ���Ϣ
	 * 
	 * @param propName
	 *            ������
	 * @return
	 */
	public static String getProper(String propName) {
		Properties proper = new Properties();
		try {
			// ���������ļ�
			proper.load(Tools.class
					.getResourceAsStream("../../../../config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proper.getProperty(propName);
	}
	
	public static int randomNumber(int digit) {
		int result=1;
		for(int i=1;i<digit;i++){
			result*=10;
		}
		//System.out.println((int)((Math.random()*9+1)*result));  
		return (int)((Math.random()*9+1)*result);
	}
	
	public static void main(String[] args) {
       /* String str = "<html src='www.aaa.com'>bb</hmtl>";
        
        Matcher matcher = Pattern.compile("html(\\s+[^>]*)").matcher(str);
        if(matcher.find()){
            String g = matcher.group(1);
            str = str.replace(g, "");
        }
        
        System.out.println(str);*/
        
        String stroutput = "<html src='www.aaa.com'>bb</hmtl>";
        String reg="<([^<>]*)>";
        stroutput.replaceAll(reg,"");
        System.out.println(stroutput);
        
        
    }
}
