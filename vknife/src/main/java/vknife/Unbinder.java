package vknife;

/**
 * 作者： mooney
 * 日期： 2018/1/31
 * 邮箱： shili_yan@sina.com
 * 描述：
 */

public interface Unbinder {
    void unbind();

    Unbinder EMPTY = new Unbinder() {
        @Override public void unbind() { }
    };
}
