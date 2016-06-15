package com.lcs.rest.mvc;


import com.lcs.entities.Lcs;
import com.lcs.entities.LcsWrapper;
import com.lcs.rest.resources.LcsWrapperResource;
import com.lcs.rest.resources.asm.LcsWrapperResourceAsm;
import com.lcs.rest.utilities.LcsUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;


@RestController
public class LcsController {
	Logger logger = LoggerFactory.getLogger(LcsController.class);

	public static final String main = "/";
	public static final String BODY_NOT_CORRECT_FORMAT = "Body format not in correct.";
	public static final String SETOFSTRING_EMPTY = "setOfStrings should not be empty";
	public static final String ALL_STRING_UNIQUE = "setOfStrings must be a Set";



	@RequestMapping("/")
   public String home() {
        return "Hello World!";
    }
	

	@RequestMapping(value = main, method = RequestMethod.POST)
	public ResponseEntity createLcs(@RequestBody LcsWrapper lcsWrapper) {
		try {
			LcsWrapperResource resource = new LcsWrapperResourceAsm().toResource(lcsWrapper);
		if (resource == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(SETOFSTRING_EMPTY);
			}
		if (!checkUnique(lcsWrapper.getSetOfStrings())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ALL_STRING_UNIQUE);
		}
			String setLcsString = getLcs(lcsWrapper.getSetOfStrings());
			LcsWrapper wrapLcs = new LcsWrapper();
			wrapLcs.getSetOfStrings().add(new Lcs(setLcsString));
			return ResponseEntity.status(HttpStatus.OK).body(wrapLcs);

		} catch (HttpMessageNotReadableException messageNotReadable) {
			logger.info("Test" + messageNotReadable.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BODY_NOT_CORRECT_FORMAT);
		}

	}

	public String getLcs(Set<Lcs> lcsSets) {
	List<String> strings = new ArrayList<String>();
		lcsSets.forEach(value -> strings.add(value.getValue()));

		int count = lcsSets.size();
		String str = "";
		do {

			for (int i = 0; i < lcsSets.size(); i += 2) {
				String first = strings.get(i);
			String second = null;
				if (strings.size() > i + 1) {
					second = strings.get(i + 1);
					str = LcsUtils.longestSubstring(first, second);
				} else {
				str = LcsUtils.longestSubstring(str, first);
				}

				count -= 2;
			}

		} while (count > 0);
		return str;
	}

	private boolean checkUnique(Set<Lcs> dupsLcs) {
		boolean result = false;
		Set<Lcs> allLcs = new HashSet<Lcs>();

		for (Lcs lcs : dupsLcs) {
			result = allLcs.add(lcs);
			if (result == false) {
				break;
			}
		}

		return result;

	}
	
	

}
