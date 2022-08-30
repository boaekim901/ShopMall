package utils;
/*
 * 현재 페이지와 관련된 정보 저장
 * -현재 페이지 번호
 * -페이지당 출력 항목 수
 * -각 페이지의 시작 게시글 번호
 */
public class Criteria {

	private int pageNum;    // 현재 페이지 번호
	private int rowsPerPage;// 페이지당 게시글 수 
	
	//생성자
	//기본값 : 페이지번호:1, 페이지당 항목 수 : 10
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int rowsPerPage) {
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		if(pageNum<=0) {
			this.pageNum = 1;
		}else {
			this.pageNum = pageNum;
		}
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}
	
	//페이지당 항목수를 20개로 제한
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage <=0 || rowsPerPage>20) {
			this.rowsPerPage = 20;
		}else {
			this.rowsPerPage = rowsPerPage;
		}
	}
	
	//각페이지에서 시작하는 게시글 번호
	public int getPageStart() {
		
		return (pageNum -1) * rowsPerPage + 1;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + ",pageStart="+ getPageStart()+"]";
	}
	
}
