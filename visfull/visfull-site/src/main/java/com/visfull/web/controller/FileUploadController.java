package com.visfull.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.visfull.web.service.impl.ConfigInfo;
import com.visfull.web.vo.UploadResult;

@Controller
@RequestMapping("/file")
public class FileUploadController implements ServletContextAware {
	
	@Autowired
	private ConfigInfo configInfo;
	
	private ServletContext servletContext;
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String commonFileUpload(@RequestParam("file")CommonsMultipartFile commonsMultipartFile,ModelMap map,HttpServletRequest request){
		UploadResult result =  new UploadResult();
		if (!commonsMultipartFile.isEmpty()) {
			   String path = configInfo.getUploadPath();//this.servletContext.getRealPath("/");
			   File posters = new File(path+"/posters/");
			   File file = new File(path+"/posters/" + new Date().getTime() + ".jpg");
			   String fileUrl = "";
			   StringBuffer base = request.getRequestURL();
			   String baseUrl = base.substring(0, base.indexOf(request.getRequestURI()));
			   baseUrl += servletContext.getContextPath();
			   try {
				   if(!posters.exists()){
					   posters.mkdir();
				   }
					commonsMultipartFile.getFileItem().write(file);
					Thumbnails.of(file).size(configInfo.getWidth(),configInfo.getHeight())
							.toFile(new File(file.getPath().replace(file.getName(), "s_"+file.getName())));
					fileUrl = file.getPath().replace(path,
							configInfo.getHostPrefix());
				   fileUrl=fileUrl.replace("\\","/");
				   result.setStatusCode("0000");
				   result.setMessage("上传成功！");
				   result.setFileUrl(fileUrl);
			   } catch (Exception e) {
				   result.setStatusCode("0001");
				   result.setMessage(e.getMessage());
				   e.printStackTrace();
			   }

		}else {
			   result.setStatusCode("0002");
			   result.setMessage("上传文件为空！");
		}
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			map.put("result", mapper.writeValueAsString(result));
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "upload_result";
	}
}
