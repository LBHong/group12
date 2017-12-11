/**
 * 
 */
package com.user.action;

/**
 * @author lenovo
 *
 */

import java.io.File;
 import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class uploadaction {
	private File upload;  
	//接受拦截器传入的临时文件  
	  
    private String uploadFileName;
    
    public String uploadimage(){
    	
    /*	  if(upload==null)  {
    		  System.out.println("camdsadsa");
    		  return "failure";
    	  }*/
    	System.out.println("cadsadas");
    	String target=ServletActionContext.getServletContext().getRealPath("/images/"+uploadFileName);
    	System.out.println(target);
    	     //获得上传的文件
    	File targetFile=new File(target);
    	  //通过struts2提供的FileUtils类拷贝
    	       try {
    	         FileUtils.copyFile(upload, targetFile);
    	    } catch (IOException e) {
    	          e.printStackTrace();
    	       }
         return "success";
      }
	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}



	/**
	 * @param upload the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}



	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}



	/**
	 * @param uploadFileName the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


}
