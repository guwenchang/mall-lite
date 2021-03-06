package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smart.core.starter.utils.CopyUtils;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity?replace("entity","param")}.${entity}Param;
import ${package.Entity?replace("entity","param")}.${entity}QueryParam;
import ${package.Entity?replace("entity","result")}.${entity}Result;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@AllArgsConstructor
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {

    private final ${table.mapperName} mapper;


    @Override
    public Boolean save(${entity}Param param) {
        ${entity} entity = CopyUtils.copyObject(param, ${entity}.class);
        int insert = mapper.insert(entity);
        return insert > 0;
    }

    @Override
    public Boolean update(${entity}Param param) {
        ${entity} entity = CopyUtils.copyObject(param, ${entity}.class);
        int update = mapper.updateById(entity);
        return update > 0;

    }

    @Override
    public Boolean delete(Long id) {
        int delete = mapper.deleteById(id);
        return delete > 0;
    }

    @Override
    public ${entity}Result get(Long id) {
        ${entity} entity = mapper.selectById(id);
        return CopyUtils.copyObject(entity, ${entity}Result.class);
    }

    @Override
    public List<${entity}Result> list(${entity}QueryParam param) {
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        List<${entity}> entityList = mapper.selectList(queryWrapper);
        return CopyUtils.copyList(entityList, ${entity}Result.class);
    }

    @Override
    public Page<${entity}Result> listPage(Page<${entity}Result> page, ${entity}QueryParam param) {
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        Page<${entity}> entityPage = new Page<>();
        entityPage.setSize(page.getSize());
        entityPage.setCurrent(page.getCurrent());
        entityPage.setAsc(page.ascs());
        entityPage.setDesc(page.descs());
        mapper.selectPage(entityPage, queryWrapper);
        Page<${entity}Result> resultPage = CopyUtils.copyPage(entityPage, ${entity}Result.class);
        return resultPage;
    }


}
</#if>
