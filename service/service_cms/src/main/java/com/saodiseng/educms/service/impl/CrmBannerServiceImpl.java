package com.saodiseng.educms.service.impl;

import com.saodiseng.educms.entity.CrmBanner;
import com.saodiseng.educms.mapper.CrmBannerMapper;
import com.saodiseng.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-04-30
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public List<CrmBanner> selectAllBanner() {
        List<CrmBanner> crmBanners = baseMapper.selectList(null);
        return crmBanners;
    }
}
