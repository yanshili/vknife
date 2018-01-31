package vknife.compiler.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者： mooney
 * 日期： 2018/1/31
 * 邮箱： shili_yan@sina.com
 * 描述：
 */

public class FileBean extends BaseBean {

    List<ViewBean> mViewBeanList;

    public List<ViewBean> getViewBeanList() {
        return mViewBeanList;
    }

    public void setViewBeanList(List<ViewBean> viewBeanList) {
        mViewBeanList = viewBeanList;
    }

    public void addViewBean(ViewBean viewBean){
        if (mViewBeanList==null){
            mViewBeanList=new ArrayList<>();
        }
        mViewBeanList.add(viewBean);
    }
}
