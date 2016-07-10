package com.img.gen.conmon;


import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 分页对象
 *
 * @author jinhangzhan
 * @version 
 * 
 */
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 返回数据
     */
    private List<T> resultData;
    
    /**
     * 附带分页其他相关信息数据
     */
    private Map<String, Object> map;
	/**
	 * 请求第一页数据
	 */
	public void firstPage() {
		pageNum = 1;
		calculateStartAndEndRow();
	}

	/**
	 * 请求最后一页数据
	 */
	public void lastPage() {
		pageNum = pages;
		calculateStartAndEndRow();
	}

	/**
	 * 请求前一页数据
	 */
	public void previousPage() {
		if (pageNum > 1) {
			pageNum--;
			calculateStartAndEndRow();
		}
	}

	/**
	 * 请求后一页数据
	 */
	public void nextPage() {
		if (pageNum < pages) {
			pageNum++;
			calculateStartAndEndRow();
		}
	}

	/**
	 * 请求转到某页
	 */
	public void gotoPage(int nPage) {
		if (nPage > 0 && nPage <= pages) {
			pageNum = nPage;
			calculateStartAndEndRow();
		}
	}
	
	/**
	 * 是否是第一页
	 * 
	 * @return boolean
	 */
	public boolean isFirstPage() {
		return pageNum == 1;
	}

	/**
	 * 是否为最后一页
	 * 
	 * @return boolean
	 */
	public boolean isLastPage() {
		return pageNum == pages;
	}
    
	public Page() {}
	public Page(int pageNum, int pageSize) {
        if (pageNum == 1 && pageSize == Integer.MAX_VALUE) {
            pageSize = 0;
        }
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        calculateStartAndEndRow();
    }

    public List<T> getResultData() {
		return resultData;
	}

	public Page<T> setResultData(List<T> resultData) {
		this.resultData = resultData;
		return this;
	}

	public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = (pageNum <= 0) ? 1 : pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public long getTotal() {
        return total;
    }

    public Page<T> setTotal(long total) {
        this.total = total;
        if (total == -1) {
            pages = 1;
            return this;
        }
        if (pageSize > 0) {
            pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
        } else {
            pages = 0;
        }
        //分页合理化，针对不合理的页码自动处理
        if (pageNum > pages) {
            pageNum = pages;
            calculateStartAndEndRow();
        }
        return this;
    }

    public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	/**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.startRow = this.pageNum > 0 ? (this.pageNum - 1) * this.pageSize : 0;
    }

}