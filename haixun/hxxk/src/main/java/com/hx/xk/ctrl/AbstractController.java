package com.hx.xk.ctrl;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hx.xk.common.XkConstant;
import com.hx.xk.dto.base.DtoResult;
import com.hx.xk.dto.base.Pager;
import com.hx.xk.service.IService;

public abstract class AbstractController<DtoType, ServiceType extends IService<DtoType>> extends BaseController {

	private Log log = LogFactory.getLog(AbstractController.class);

	abstract protected ServiceType getService();

	public DtoResult create(DtoType dto, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().create(dto));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			re = resultService.retrieveByCode("030002");
			log.error("create::" + e);
		}
		return re;
	}

	// public DtoResult update(DtoType dto, HttpSession session) {
	// DtoResult re = new DtoResult();
	// try {
	// re.setResult(getService().update(dto));
	// re.setCode(XkConstant.RESULT_CODE_SUCCESS);
	// } catch (Exception e) {
	// re = resultService.retrieveByCode("030001");
	// log.error("update::" + e);
	// }
	// return re;
	// }

	// public DtoResult updateProperties(DtoType dto, HttpSession session) {
	// DtoResult re = new DtoResult();
	// try {
	// re.setResult(getService().updateProperties(dto));
	// re.setCode(XkConstant.RESULT_CODE_SUCCESS);
	// } catch (Exception e) {
	// re = resultService.retrieveByCode("030001");
	// log.error("update::" + e);
	// }
	// return re;
	// }
	public DtoResult createOrUpdate(DtoType dto, Object id, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().createOrUpdate(dto, id));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_UPDATE_FAILURE");
			log.error("createOrUpdate::" + e);
		}
		return re;
	}

	public DtoResult delete(DtoType dto, HttpSession session, Object id) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().delete(id));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			if (e instanceof ConstraintViolationException) {
				re.setResult("XkConstant.M_FK_CONSTRAINT_VIOLATION");
			} else {
				re.setResult("XkConstant.M_DELETION_FAILURE");
			}
			log.error("delete::" + e);
		}
		return re;
	}

	public DtoResult deleteList(long[] ids, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			DtoResult lastResult = null;
			for (Object id : ids) {
				lastResult = getService().delete(id);
			}
			re.setResult(lastResult);
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_BATCH_DELETION_FAILURE");
			log.error("deleteList::" + e);
		}
		return re;
	}

	public DtoResult deleteArticlesList(long[] ids, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			DtoResult lastResult = null;
			for (Object id : ids) {
				lastResult = getService().delete(id);
			}
			re.setResult(lastResult);
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_BATCH_DELETION_FAILURE");
			log.error("deleteArticlesList::" + e);
		}
		return re;
	}

	public DtoResult retrieveList(@RequestParam("pageNow") int pageNow, @RequestParam("pageSize") int pageSize,
			HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().retrieveList(pageNow, pageSize));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("retrieveList.success!");
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_RETRIEVE_LIST_FAILURE");
			log.error("retrieveList::" + e);
		}
		return re;
	}

	public Pager retrieveList(Pager pager, HttpSession session) {
		// DtoResult re = new DtoResult();
		try {
			pager = getService().retrieveList(pager);
			// re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("retrieveList.success!");
		} catch (Exception e) {
			pager = new Pager();
			pager.setCode("XkConstant.RESULT_CODE_FAILURE");
			pager.setResult("XkConstant.M_RETRIEVE_LIST_FAILURE");
			log.error("retrieveList::" + e);
		}
		return pager;
	}

	public DtoResult retrieveById(long id, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().retrieveById(id));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("retrieveById.success!");
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_RETRIEVE_LIST_FAILURE");
			log.error("retrieveById::" + e);
		}
		return re;
	}

	public DtoResult findByProperties(Map<String, Object> whereProperties, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().retrieveByProperties(whereProperties));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("findByProperties.success!");
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_RETRIEVE_BY_PROPERTIES_FAILURE");
			log.error("findByProperties::" + e);
		}
		return re;
	}

	public DtoResult findByProperties(@RequestParam("pageNow") int pageNow, @RequestParam("pageSize") int pageSize,
			Map<String, Object> whereProperties, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().retrieveByProperties(whereProperties, pageNow, pageSize));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("findByProperties.success!");
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_RETRIEVE_BY_PROPERTIES_FAILURE");
			log.error("findByProperties::" + e);
		}
		return re;
	}

	public DtoResult findByDto(DtoType dto, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().retrieveByDto(dto));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("findByDto.success!");
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_RETRIEVE_BY_PROPERTIES_FAILURE");
			log.error("findByDto::" + e);
		}
		return re;
	}

	public DtoResult findByDto(@RequestParam("pageNow") int pageNow, @RequestParam("pageSize") int pageSize,
			DtoType dto, HttpSession session) {
		DtoResult re = new DtoResult();
		try {
			re.setResult(getService().retrieveByDto(dto, pageNow, pageSize));
			re.setCode(XkConstant.RESULT_CODE_SUCCESS);
			log.info("findByDto.success!");
		} catch (Exception e) {
			re.setCode("XkConstant.RESULT_CODE_FAILURE");
			re.setResult("XkConstant.M_RETRIEVE_BY_PROPERTIES_FAILURE");
			log.error("findByDto::" + e);
		}
		return re;
	}

	protected String uploadFile(MultipartFile file, String type, HttpSession session) {
		String fileName = null;
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			String path = session.getServletContext().getRealPath("/");
			fileName = "/" + XkConstant.URL_UPLOAD_FOLDER + "/" + type + "/" + file.getOriginalFilename();
			// + UUID.randomUUID() + "."
			// + DdsUtil.getFileExtension(file.getOriginalFilename());
			// fileName = LsspConstant.UPLOAD_FOLDER_IMAGE + File.separator
			// + LsspConstant.UPLOAD_FOLDER_IMAGE_HOSPITAL
			// + File.separator + UUID.randomUUID() + "."
			// + LsspUtil.getFileExtension(file.getOriginalFilename());
			log.info("uploadFile.path=" + path + File.separator + fileName);
			File targetFile = new File(path, fileName);

			if (!targetFile.exists()) {
				targetFile.mkdirs();
				log.info("uploadFile.mkdirs");
			}

			// 保存
			try {
				file.transferTo(targetFile);
				log.info("uploadFile.filename=" + fileName);
			} catch (Exception e) {
				log.error("uploadFile::" + e);
			}
		}
		return fileName;
	}

}