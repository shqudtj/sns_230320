package com.sns.comment.domain;

import java.time.ZonedDateTime;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Comment {

	private int id;
	private int postId;
	private int userId;
	private String content;
	private ZonedDateTime createdAt;
	private ZonedDateTime updatedAt;
}
