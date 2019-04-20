package org.tree.ktv.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tree.ktv.vo.TestVO;

/**
 * @author er_dong_chen
 * @date 2019/3/18
 */
@RestController
public class TestController {

    @RequestMapping("/test.do")
    public void test(TestVO vo){
        return;
    }


}
