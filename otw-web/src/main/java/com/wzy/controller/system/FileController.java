package com.wzy.controller.system;


import com.wzy.common.ResultCode;
import com.wzy.common.ResultMsg;
import com.wzy.domain.File;
import com.wzy.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author zzw
 * @since 2018-08-01
 */
@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController extends AbstractController {

	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileService fileService;

	/**
	 * 文件上传
	 * @param fileName
	 * @return
	 */
	@PostMapping("/uploadFile")
	@ResponseBody
	public ResultMsg uploadFile(MultipartFile[] fileName, HttpSession session, HttpServletRequest request){
		try {
			String realPath = session.getServletContext().getRealPath("/uploads/");
			java.io.File temp = new java.io.File(realPath);
			if(!temp.exists()){
				//如果不存在，就新建一个路径
				temp.mkdir();
			}
			File file = new File ();
			if(fileName !=null && fileName.length > 0){
				for (MultipartFile multipartFile : fileName) {
					String name = multipartFile.getOriginalFilename ();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					String format = sdf.format(new Date());
					name = name.substring(0,name.lastIndexOf("."))+"_"+format+name.substring(name.lastIndexOf("."));
					java.io.File f = new java.io.File(realPath+ java.io.File.separator+name);
					multipartFile.transferTo(f);

					file = new File ().setName (name).setPath (realPath+ name).setCreatedAt (new Date ()).setMime (multipartFile
							.getContentType ()).setType (name.substring(name.lastIndexOf(".")+1));
					fileService.insert(file);
				}
			}
			return ResultMsg.success (file);
		} catch (IOException e) {
			e.printStackTrace ();
			return ResultMsg.create ( ResultCode.ERROR_UPLOAD_FAILED,ResultCode.ERROR_UPLOAD_FAILED_MSG);
		}
	}

/*
	*/
/**
	 * 删除文件
	 * @param param
	 * @return
	 *//*

	@PostMapping("/deleteFile")
	@ResponseBody
	@Permission
	public ResultMsg deleteFile(@RequestBody JSONObject param) throws NoSuchFieldException {
		if(param.containsKey("data")) {
			File file = param.getObject ("data",File.class);
			try{
				file.setDeletedAt (new Date());
				fileService.updateById (file);
				fastdfsUtil.deleteFile (file.getPath ());
				return ResultMsg.success ();
			}catch (RuntimeException e){
				return ResultMsg.create (StatusCode.ERROR_QUERY_FAILED,StatusCode.ERROR_QUERY_FAILED_MSG);
			}
		}else{
			return ResultMsg.create (StatusCode.ERROR_PARAMETER_NOT_CORRECT,StatusCode.ERROR_PARAMETER_NOT_CORRECT_MSG);
		}
	}
*/
		/*
		 * 在线预览图片
		 */
		@RequestMapping(value = "/showImage",method = RequestMethod.GET)
		@ResponseBody
		public void showImage(String path,HttpServletResponse response,HttpServletRequest request) throws IOException {
			response.setContentType("text/html; charset=UTF-8");
			response.setContentType("image/jpeg");
			String fullFileName = request.getSession().getServletContext().getRealPath("/uploads/" + path);
			FileInputStream fis = new FileInputStream(fullFileName);
			OutputStream os = response.getOutputStream();
			try {
				int count = 0;
				byte[] buffer = new byte[1024 * 1024];
				while ((count = fis.read(buffer)) != -1){
					os.write(buffer, 0, count);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (os != null){
					os.close();
				}
				if (fis != null){
					fis.close();
				}
			}
}

}

