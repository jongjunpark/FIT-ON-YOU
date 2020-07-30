package com.web.curation.controller.follow;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.dao.follow.SearchDao;
import com.web.curation.model.follow.Search;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" })
@RestController
public class SearchController {

	@Autowired
	SearchDao searchDao;

	// ariticleNo만 가져다쓰면됨 왜 모든 컬럼을 가져와야 작동하는 지 이해를 할수 없음 걍외우셈
	@GetMapping("/search/hash")
	@ApiOperation(value = "검색")
	public List<Search> searchHash(@Valid @RequestParam String findContent) {
		StringTokenizer st = new StringTokenizer(findContent);
		List<String> list = new ArrayList<>();
		List<Search> result = new ArrayList<>();
		while (st.hasMoreTokens()) {
			String input = st.nextToken("#");
			System.out.println("input================>" + input);
			list.add(input);
		}
		int size = list.size();
		System.out.println(size);
		if (size == 1) {
			String name1 = list.get(0);
			result = searchDao.getSearchByTagnameOne(name1);

		} else if (size == 2) {
			String name1 = list.get(0);
			String name2 = list.get(1);
			result = searchDao.getSearchByTagnameTwo(name1, name2);
		} else if (size == 3) {
			String name1 = list.get(0);
			String name2 = list.get(1);
			String name3 = list.get(2);
			result = searchDao.getSearchByTagnameThree(name1, name2, name3);
		} else if (size == 4) {
			String name1 = list.get(0);
			String name2 = list.get(1);
			String name3 = list.get(2);
			String name4 = list.get(3);
			result = searchDao.getSearchByTagnameFour(name1, name2, name3, name4);
		} else if (size == 5) {
			String name1 = list.get(0);
			String name2 = list.get(1);
			String name3 = list.get(2);
			String name4 = list.get(3);
			String name5 = list.get(4);
			result = searchDao.getSearchByTagnameFive(name1, name2, name3, name4, name5);
		} else {
			System.out.println("값이 너무 많습니다.");
		}

		if (!result.isEmpty()) {
			System.out.println(result.toString());
		}
		return result;
	}

}
