package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeline")
@Controller
public class TimeLineController {

	@RequestMapping("/timeline_view")
	public String timeLineView(Model model) {
		
		model.addAttribute("view", "timeline/timeline");
		return "template/layout";
	}
}
