package com.hust.bigdata.controller;

import com.hust.bigdata.common.R;
import com.hust.bigdata.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MyController {

    @Autowired
    MyService myService;

    //详情
    @GetMapping("/detail")
    public <T> R<T> detail(int tableId, int itemId){
        R<T> result = myService.detail(tableId, itemId);
        return result;
    }


    //修改
    @PutMapping("/modify")
    public <T> R<T> modify(int tableId, int itemId, @RequestBody String json){

        boolean success = myService.modify(tableId, itemId, json);
        if(success){
            //return R.success("修改成功");
            R<T> result = (R<T>) R.success("修改成功");
            return result;
        }else{
            return R.error("修改失败");
        }
    }

    //分页查询
    @GetMapping("/show")
    public <T> R<T> show(int tableId, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        R<T> result = myService.show(tableId, pageNo, pageSize, null);
        return result;
    }

    //删除
    @DeleteMapping("/delete")
    public <T> R<T> delete(int tableId, int itemId){
        boolean success = myService.delete(tableId, itemId);
        if(success){
            return (R<T>) R.success("删除成功");
        }else{
            return R.error("删除失败");
        }
    }

    //新增
    @PostMapping("/new")
    public <T> R<T> addNew(int tableId,@RequestBody String json){
        boolean success = myService.addNew(tableId, json);
        if(success){
            return (R<T>) R.success("增加成功");
        }else{
            return R.error("删除成功");
        }
    }

    //模糊检索
    @GetMapping("/search")
    public <T> R<T> search(int tableId, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(required = false) String s){
        R<T> result = myService.show(tableId, pageNo, pageSize, s);
        return result;
    }

    //精确搜索
    @GetMapping("/searchmore")
    public <T> R<T> searchMore(int tableId, @RequestParam(defaultValue = "1") int pageNo, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(required = false) String s){
        R<T> result = myService.search(tableId, pageNo, pageSize, s);
        return result;
    }

}
