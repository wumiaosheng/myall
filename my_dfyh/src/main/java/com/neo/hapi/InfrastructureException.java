/**
 * $Author: wuym $
 * $Rev: 155 $
 * $Date:: 2012-07-09 15:08:37#$:
 *
 * Copyright (C) 2012 Seeyon, Inc. All rights reserved.
 *
 * This software is the proprietary information of Seeyon, Inc.
 * Use is subject to license terms.
 */
package com.neo.hapi;

/**
 * <p>Title: T1开发框架</p>
 * <p>Description: 框架异常类<code></p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: seeyon.com</p>
 */
public final class InfrastructureException extends RuntimeException {

    public InfrastructureException() {
    }

    public InfrastructureException(String message) {
        super(message);
    }

    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfrastructureException(Throwable cause) {
        super(cause);
    }
}