/**
 * 
 */
package com.hx.xk.ctrl;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import com.hx.xk.common.XkConstant;
import com.hx.xk.common.util.XkUtil;
import com.hx.xk.dto.DtoWxaccount;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.service.IResultService;

/**
 * @author Hasan
 * 
 */
public class BaseController {
	private Log log = LogFactory.getLog(BaseController.class);

	@Resource
	protected IResultService resultService;

	protected DtoResult checkLogin(HttpSession session) {
		DtoWxaccount user = (DtoWxaccount) session.getAttribute(XkConstant.V_SESSION_WXACCOUNT);
		DtoResult result = new DtoResult();
		if (user == null) {
			result.setCode(XkConstant.RESULT_CODE_USER_NOT_LOGIN);// =
																	// resultService.retrieveByCode("921129");//
																	// 用户未登录
			return result;
		}
		result.setResult(user);
		return result;
	}

	protected String uploadFile(MultipartFile file, String type, HttpSession session) {
		String fileName = null;
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			String path = session.getServletContext().getRealPath(XkConstant.URL_UPLOAD_FOLDER);
			fileName = XkConstant.URL_UPLOAD_FOLDER_IMAGE + "/" + type + "/" + UUID.randomUUID() + "."
					+ XkUtil.getFileExtension(file.getOriginalFilename());
			// fileName = LsspConstant.UPLOAD_FOLDER_IMAGE + File.separator
			// + LsspConstant.UPLOAD_FOLDER_IMAGE_HOSPITAL
			// + File.separator + UUID.randomUUID() + "."
			// + LsspUtil.getFileExtension(file.getOriginalFilename());
			log.info("uploadFile.path=" + path + "/" + fileName);
			File targetFile = new File(path, fileName);

			if (!targetFile.exists()) {
				targetFile.mkdirs();
				log.info("uploadFile.mkdirs");
			}

			// 保存
			try {
				file.transferTo(targetFile);
				// 上传到云端服务器
				String objectkey = XkConstant.URL_UPLOAD_FOLDER + "/" + fileName;
				String orgpath = path + File.separator + fileName;
				// aliyuncsService.uploadFile(objectkey, orgpath);

				log.info("uploadFile.filename=" + fileName);
			} catch (Exception e) {
				log.error("uploadFile::" + e);
			}
		}
		return XkConstant.URL_UPLOAD_FOLDER + "/" + fileName;
	}

}
