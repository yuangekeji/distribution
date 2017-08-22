package com.distribution.common.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Encapsulate all properties and operations related to Pagination.
 * 
 * This Class does not have dependency with any ORM framework and Database
 * system.
 * 
 * All sequence number are started with 1.
 * 
 * The idea of this class is inspired from SpringSide project.
 * 
 * @param <T>
 *            T is referred to specified Entity class.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination<T> {
	// -- Constant definitions of sort order --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	// -- Internal parameters --//
	protected int pageNo = 1;
	protected int pageSize = 10;
	protected int currentPageSize = 0;
	// By default all pagination should be sorted by id field in descending
	// order.
	protected String orderBy = "id";
	protected String order = DESC;

	protected boolean autoCount = true;

	// -- Result of query --//
	protected List<T> result = new ArrayList();
	protected long totalCount = -1;
	protected long pageCount = -1;
	protected int start=0;

	public Map<String,Object> parameterMap = new HashMap<String,Object>();

	public Pagination() {
	}

	public Pagination(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(final int pageNo) {
		this.pageNo = (pageNo < 1) ? 1 : pageNo;
		getStart();
	}

	/**
	 * Used for method chain.
	 */
	public Pagination<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Used for method chain.
	 */
	public Pagination<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * Get the offset of first record in current page.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Set order by field, if multiple fields then separate by comma.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Used for method chain.
	 */
	public Pagination<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	public String getOrder() {
		return order;
	}

	/**
	 * Set multiple order fields.
	 * 
	 * @param order
	 *            .
	 */
	public void setOrder(final String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		// Validate the order by setting.
		String[] orders = StringUtils.split(lowcaseOrder, ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr)) {
				throw new IllegalArgumentException("Invalid order by setting:" + orderStr);
			}
		}

		this.order = lowcaseOrder;
	}

	/**
	 * Used for method chain.
	 */
	public Pagination<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}


	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	public long getPageCount() {
		if (totalCount < 0) {
			return -1;
		}

		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount++;
		}
		return pageCount;
	}

	public boolean hasNext() {
		return (pageNo + 1 <= getPageCount());
	}

	// for the convenience of using with jstl
	public boolean isHasNext() {
		return hasNext();
	}

	public int getNextPageNo() {
		if (hasNext()) {
			return pageNo + 1;
		} else {
			return pageNo;
		}
	}

	public boolean hasPre() {
		return (pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (hasPre()) {
			return pageNo - 1;
		} else {
			return pageNo;
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	public int getCurrentPageSize() {
		if (this.totalCount == 0) return 0;
		this.currentPageSize = (int) (this.totalCount - (long) (this.pageSize * (this.pageNo - 1)));
		if (this.currentPageSize > this.pageSize) this.currentPageSize = this.pageSize;
		if (this.currentPageSize <= 0) return 10;
		return currentPageSize;
	}

	public void setCurrentPageSize(int currentPageSize) {
		this.currentPageSize = currentPageSize;
	}
}
