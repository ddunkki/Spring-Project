package com.board.web.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant {
	
	//lombok이 자동으로 만들어 놓은 setter을 이용해서 DI적용
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
