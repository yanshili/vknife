package vknife.compiler.bean;

/**
 * 作者： mooney
 * 日期： 2018/1/31
 * 邮箱： shili_yan@sina.com
 * 描述：
 */

public class ViewBean extends BaseBean{


    private String fieldName;

    private String fieldType;

    private int viewId;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getViewId() {
        return viewId;
    }

    public void setViewId(int viewId) {
        this.viewId = viewId;
    }
}
