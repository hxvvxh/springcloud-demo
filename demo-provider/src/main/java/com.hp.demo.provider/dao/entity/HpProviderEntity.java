package com.hp.demo.provider.dao.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 21:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HpProviderEntity implements Serializable {
    private Long id;

    private String name;

    private String pass;

    private Date createAt;
}
