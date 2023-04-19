package com.rhq.parkinglotsystem.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageInfo;
import com.rhq.parkinglotsystem.common.ResponseResult;
import com.rhq.parkinglotsystem.common.UpLoadImage;
import com.rhq.parkinglotsystem.modle.Condition.StaffCondition;
import com.rhq.parkinglotsystem.modle.Staff;
import com.rhq.parkinglotsystem.modle.request.staffre;
import com.rhq.parkinglotsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 获取职工列表（包含条件查询）
     * @param condition 查询条件
     * @return 职工列表
     */
    @RequestMapping(value = "/getlist",method = RequestMethod.POST)
    public ResponseResult<PageInfo<Staff>> FindAllStaffs(@RequestBody StaffCondition condition){
        List<Staff> staffList = staffService.FindAllStaffs(condition);
        PageInfo<Staff> pageInfo = new PageInfo<>(staffList);
        return staffList != null ? ResponseResult.Success(pageInfo,"成功!") : ResponseResult.Failed("失败!");
    }

    /**
     * 保存职工
     * @param entity 职工信息
     * @return 数据库影响条数
     */
    @PostMapping("/save")
    public ResponseResult<Integer> SaveStaff(@RequestBody Staff entity){
        if (entity != null){
            int result = staffService.SaveStaff(entity);
            return result != 0 ? ResponseResult.Success(result,"保存成功！"):ResponseResult.Failed("保存失败!");
        }else{
            return ResponseResult.Failed("无效对象，保存失败!");
        }
    }

    /**
     * 保存职工
     * @param Id 职工Id
     * @return 数据库影响条数
     */
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    public ResponseResult<Integer> DeleteStaff(@RequestParam String Id){
       if (StringUtils.isNotBlank(Id)){
           int result = staffService.DeleteStaff(Id);
           return result != 0 ? ResponseResult.Success(result,"删除成功！"):ResponseResult.Failed("删除失败!");
       }
       else{
           return ResponseResult.Failed("删除失败!");
       }
    }

    /**
     * 返回图片上传后存储的地址
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    public String uploadImage(@RequestParam(value = "file") MultipartFile multipartFile){
        if (multipartFile.isEmpty()){
            return "文件有误！";
        }
        return UpLoadImage.uploadImage(multipartFile);
    }
}
