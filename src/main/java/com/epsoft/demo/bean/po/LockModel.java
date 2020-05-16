package com.epsoft.demo.bean.po;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LockModel {

	 private String lock_key;
     private String request_id;
     private Integer lock_count;
     private Long timeout;
     private Integer version;
	
}
