package org.svnadmin.controller.svn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.svnadmin.common.annotation.AdminAuthPassport;
import org.svnadmin.common.entity.PageBean;
import org.svnadmin.common.web.BaseController;
import org.svnadmin.entity.PjGr;
import org.svnadmin.service.PjGrService;
import org.svnadmin.service.PjService;
import org.svnadmin.service.UsrService;

/**
 * SVN项目用户组管理控制器
 * @author Zoro
 * @datetime 2016/1/20 19:48
 * @since 1.0.0
 */
@Controller
public class ProjectGrController extends BaseController {

    @Autowired
    private UsrService usrService;
    @Autowired
    private PjService pjService;
    @Autowired
    private PjGrService pjGrService;

    /**
     * 项目用户组列表
     * @param session
     * @return
     */
    @RequestMapping(value = "pjGrList", method = RequestMethod.GET)
    public String pjGrList(HttpSession session,@RequestParam("pj")String pj, ModelMap map) {
        map.put("pj", pjService.get(pj));
        return "svn/pj_gr_list";
    }

    /**
     * 项目用户组列表（数据集）
     * @param session
     * @return
     */
    @RequestMapping(value = "pjGrList", method = RequestMethod.GET, params = "action=data")
    @ResponseBody
    public Object pjGrList(HttpSession session,@RequestParam("pj")String pj) {
        // 项目账户
        List<PjGr> list = pjGrService.list(pj);
        PageBean<PjGr> pageBean = new PageBean<PjGr>();
        pageBean.setRecordCount(list.size());
        pageBean.setDataList(list);
        return pageBean;
    }

    /**
     * 添加项目用户组处理
     * @param request
     * @return
     */
    @AdminAuthPassport
    @RequestMapping(value = "pjGrAddHandler", method = RequestMethod.POST)
    @ResponseBody
    public Object pjGrAddHandler(HttpServletRequest request,PjGr entity) {
        pjGrService.save(entity);
        return pushMsg("添加项目用户组成功", true);
    }

    /**
     * 删除项目用户组处理
     * @param request
     * @return
     */
    @AdminAuthPassport
    @RequestMapping(value = "pjGrRemoveHandler", method = RequestMethod.POST)
    @ResponseBody
    public Object pjGrRemoveHandler(HttpServletRequest request,PjGr entity) {
        pjGrService.delete(entity.getPj(), entity.getGr());
        return pushMsg("删除项目用户组成功", true);
    }
}
