package ptithcm.service;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;

public interface PageService {
	@SuppressWarnings({"rawtypes" })
	<E> PagedListHolder getPageList(List<E> list, int page, int maxrow);
}
