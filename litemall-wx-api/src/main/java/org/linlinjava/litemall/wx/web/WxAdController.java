package org.linlinjava.litemall.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallAd;
import org.linlinjava.litemall.db.service.LitemallAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wx/ad")
@Validated
public class WxAdController {
    private final Log logger = LogFactory.getLog(WxAdController.class);

    @Autowired
    private LitemallAdService adService;

    /**
     * 活动列表
     */
    @RequestMapping("/list")
    public Object list(@RequestParam(defaultValue = "1") Integer position) {
        List<LitemallAd> adList = adService.queryByPosition(position.byteValue());
        return ResponseUtil.okList(adList);
    }

}
