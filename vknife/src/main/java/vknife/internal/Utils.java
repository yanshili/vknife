package vknife.internal;

import android.view.View;

/**
 * 作者： mooney
 * 日期： 2018/1/31
 * 邮箱： shili_yan@sina.com
 * 描述：
 */

public class Utils {

    public static <T> T findRequiredViewAsType(View source, int id, String who, Class<T> cls) {
        View view = findViewById(source, id, who);
        return castView(view, id, who, cls);
    }

    public static View findViewById(View source, int id, String who){
        View view=source.findViewById(id);
        if (view!=null){
            return view;
        }
        String name = getResourceEntryName(source, id);
        throw new IllegalStateException("Required view '"
                + name
                + "' with ID "
                + id
                + " for "
                + who
                + " was not found. If this view is optional add '@Nullable' (fields) or '@Optional'"
                + " (methods) annotation.");

    }

    private static <T> T castView(View view, int id, String who, Class<T> cls) {
        try {
            return cls.cast(view);
        } catch (ClassCastException e) {
            String name = getResourceEntryName(view, id);
            throw new IllegalStateException("View '"
                    + name
                    + "' with ID "
                    + id
                    + " for "
                    + who
                    + " was of the wrong type. See cause for more info.", e);
        }
    }


    private static String getResourceEntryName(View view, int id) {
        if (view.isInEditMode()) {
            return "<unavailable while editing>";
        }
        return view.getContext().getResources().getResourceEntryName(id);
    }

    private Utils() {
        throw new AssertionError("No instances.");
    }
}
