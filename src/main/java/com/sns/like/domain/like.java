package com.sns.like.domain;

import java.util.Date;

import lombok.Data;

@Data
public class like {

	private int postId;
	private int userId;
	private Date createdAt;
}
