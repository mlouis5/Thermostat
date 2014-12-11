/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mac.thermostat.resources.annotations;

import com.mac.thermostat.resources.annotations.enums.RestType;

/**
 *
 * @author Mac
 */
public @interface RequestType {
    RestType[] types() default {RestType.GET};
}
