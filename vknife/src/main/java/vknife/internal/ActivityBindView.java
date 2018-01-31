package vknife.internal;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import vknife.Unbinder;

/**
 * 作者： mooney
 * 日期： 2018/1/31
 * 邮箱： shili_yan@sina.com
 * 描述：
 */

public class ActivityBindView implements Unbinder {

    private Activity target;

    public ActivityBindView(Activity target) {
        this(target, target.getWindow().getDecorView());
    }

    public ActivityBindView(Activity target, View source){
        this.target = target;

        TextView view= Utils.findRequiredViewAsType(source,1,"", TextView.class);
    }

    @Override
    public void unbind() {
        Activity target = this.target;
        if (target == null) throw new IllegalStateException("Bindings already cleared.");
        this.target = null;

//        target.mTextView = null;

//        view2131427412.setOnClickListener(null);
//        view2131427412 = null;
    }
}
