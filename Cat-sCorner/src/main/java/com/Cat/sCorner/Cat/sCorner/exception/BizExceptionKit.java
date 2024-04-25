package com.Cat.sCorner.Cat.sCorner.exception;

import cn.hutool.core.util.StrUtil;

public class BizExceptionKit {
    public static BizException of(String message, Object... args) {
        return of(Status.Unknown, null, message, args);
    }

    public static BizException of(Throwable cause, String message, Object... args) {
        return of(Status.Unknown, cause, message, args);
    }

    public static BizException of(Status code, String message, Object... args) {
        return of(code, null, message, args);
    }

    public static BizException of(Status code, Throwable cause, String message, Object... varargs) {
        return new BizException(code, StrUtil.format(message, (Object[]) varargs), cause);
    }
}
