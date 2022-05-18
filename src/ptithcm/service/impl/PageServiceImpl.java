package ptithcm.service.impl;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

import ptithcm.service.PageService;

public class PageServiceImpl implements PageService{

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <E> PagedListHolder getPageList(List<E> list, int page, int maxrow) {
		PagedListHolder pagedListHolder = new PagedListHolder(list);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(maxrow);
		return pagedListHolder;
	}

}
