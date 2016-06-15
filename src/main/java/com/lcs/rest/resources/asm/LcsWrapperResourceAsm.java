package com.lcs.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.lcs.entities.LcsWrapper;
import com.lcs.rest.mvc.LcsController;
import com.lcs.rest.resources.LcsWrapperResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;




public class LcsWrapperResourceAsm extends ResourceAssemblerSupport<LcsWrapper, LcsWrapperResource> {

	public LcsWrapperResourceAsm() {
		super(LcsController.class, LcsWrapperResource.class);
		
	}

	@Override
	public LcsWrapperResource toResource(LcsWrapper entity) {
		 LcsWrapperResource resource = new LcsWrapperResource();
	        resource.setSetOfStrings(entity.getSetOfStrings());
	        resource.add(linkTo(LcsController.class).slash("lcs").withRel("owner"));

	        return resource;
	}

}
