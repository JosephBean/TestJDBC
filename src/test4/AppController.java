package test4;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppController {
	
	private Scanner scan;
	private String name;
	private DBConnection db;
	private String sql;
	
	public AppController() {
		this.scan = new Scanner(System.in);
		this.db = new DBConnection();
	}

	/****************************************************************/
	// 1단계 테이블 생성하기
	private void 테이블생성(Connection conn) {
		sql = "create or replace table userinfo ("; // DDL 추가
		sql += " empno varchar(50),";
		sql += " name varchar(50),";
		sql += " email varchar(50),";
		sql += " deptname varchar(50),";
		sql += " position varchar(50),";
		sql += " phone varchar(50),";
		sql += " age int,";
		sql += " isused boolean,";
		sql += " createdate date,";
		sql += " updatedate date";
		sql += " )";
		System.out.println("테이블 생성 SQL문 확인 : " + sql);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.		
		db.테이블생성(conn, sql);
	}
	
	private void 초기데이터(Connection conn) {
		sql = "insert into userinfo"; // DML 추가
		sql += "(empno, name, email, deptname, position, phone, age, isused, createdate, updatedate)";
		sql += "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("SQL문 확인 : " + sql);
		
		DbTable data = new DbTable();
		data.setEmpno("2405001");
		data.setName("카리나");
		data.setEmail("karina@email.com");
		data.setDeptname("에스파");
		data.setPosition("가수");
		data.setPhone("01012345678");
		data.setAge(25);
		data.setIsused(true);
		data.setCreatedate("2020-01-17");
		data.setUpdatedate("2024-02-03");
		//System.out.println(data);
		db.데이터입력하기(conn, sql, data);
		
		data = new DbTable();
		data.setEmpno("2405002");
		data.setName("원터");
		data.setEmail("winter@email.com");
		data.setDeptname("에스파");
		data.setPosition("가수");
		data.setPhone("01023456789");
		data.setAge(24);
		data.setIsused(true);
		data.setCreatedate("2020-01-17");
		data.setUpdatedate("2023-12-20");
		//System.out.println(data);
		db.데이터입력하기(conn, sql, data);
		
		data = new DbTable();
		data.setEmpno("2405003");
		data.setName("김채원");
		data.setEmail("cheawon@email.com");
		data.setDeptname("르세라핌");
		data.setPosition("가수");
		data.setPhone("101034567891");
		data.setAge(25);
		data.setIsused(true);
		data.setCreatedate("2022-05-02");
		data.setUpdatedate("2023-06-15");
		//System.out.println(data);
		db.데이터입력하기(conn, sql, data);
		
		data = new DbTable();
		data.setEmpno("2405004");
		data.setName("강동원");
		data.setEmail("dongwon@email.com");
		data.setDeptname("AA그룹");
		data.setPosition("배우");
		data.setPhone("01045678912");
		data.setAge(43);
		data.setIsused(true);
		data.setCreatedate("2003-05-31");
		data.setUpdatedate("2019-02-05");
		//System.out.println(data);
		db.데이터입력하기(conn, sql, data);
		
		data = new DbTable();
		data.setEmpno("2405005");
		data.setName("원빈");
		data.setEmail("wonbin@email.com");
		data.setDeptname("AA그룹");
		data.setPosition("배우");
		data.setPhone("01056789123");
		data.setAge(47);
		data.setIsused(true);
		data.setCreatedate("1997-06-01");
		data.setUpdatedate("2015-09-07");
		//System.out.println(data);
		db.데이터입력하기(conn, sql, data);
	}
	
	// 2단계 생성된 테이블에 데이터 입력하기
	private void 입력하기(Connection conn) {
		sql = "insert into userinfo"; // DML 추가
		sql += "(empno, name, email, deptname, position, phone, age, isused, createdate, updatedate)";
		sql += "value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		Date toDay = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String day = dateFormat.format(toDay);
		
		data.setEmpno("2405006");
		data.setName("마동석");
		data.setEmail("onepunch@email.com");
		data.setDeptname("빅펀치엔터테인먼트");
		data.setPosition("행동대장");
		data.setPhone("0100020001");
		data.setAge(53);
		data.setIsused(true);
		data.setCreatedate(day);
		data.setUpdatedate(day);
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터입력하기(conn, sql, data);
	}
	
	// 3단계 생성된 데이터 가져오기 >> 목록 출력 시 화면출력() 메소드를 사용하시오.
	private void 가져오기(Connection conn) {
//		sql = "select * from userinfo"; // DML 추가
		sql = "select empno, name, email, deptname, position, phone, age, ";
		sql += " case when isused = 1 then '제직중' else '퇴사' END AS isused2, ";
		sql += " DATE_FORMAT(createdate, '%Y-%m-%d') AS createdate, ";
		sql += " DATE_FORMAT(updatedate, '%Y-%m-%d') AS updatedate ";
		sql += " from userinfo ";
		sql += " ORDER BY createdate desc";
		System.out.println("SQL문 확인 : " + sql);
		화면출력(db.데이터가져오기(conn, sql));
	}
	
	// 4단계 생성된 데이터 수정하기
	private void 수정하기(Connection conn) {
		sql = "update userinfo set name = ?, deptname = ?, phone = ?, isused = ?, updatedate = ? where empno = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		Date toDay = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String day = dateFormat.format(toDay);
		
		data.setEmpno("2405002");
		data.setName("겨울");
		data.setDeptname("솔로가수");
		data.setPhone("0102222222");
		data.setIsused(false);
		data.setUpdatedate(day);
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터수정하기(conn, sql, data);
	}
	
	// 5단계 생성된 데이터 삭제하기
	private void 삭제하기(Connection conn) {
		sql = "update userinfo set updatedate = ?, isused = ? where empno = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();

		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		Date toDay = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String day = dateFormat.format(toDay);
		data.setEmpno("2405005");
		data.setIsused(false);
		data.setUpdatedate(day);

		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터삭제하기(conn, sql, data);
	}
	/****************************************************************/
	
	private void 사용자() {
		System.out.println("당신은 누구인가요?");
		this.name = scan.nextLine();
		System.out.println(this.name + "님 환영합니다.");
	}
	
	private void 종료() {
		System.out.println(this.name + "님 다음에 또 찾아주세요.");
		scan.close();
	}
	
	private void 화면출력(List<DbTable> list) {
		for(int i = 0; i < list.size(); i++) {
			DbTable data = list.get(i);
			System.out.println(data);
		}
	}
	
	private boolean 디비접속() {
		boolean result = false;
		boolean key = true;
		System.out.println("데이터베이스에 연결하시겠습니까?(Y/N)");
		while(key) {
			switch (scan.nextLine()) {
				case "Y":
				case "y":
					result = true;
				case "N":
				case "n":
					key = false;
					break;
				default:
					System.out.println("잘못 입력하셨습니다.");
					break;
			}
		}
		return result;
	}
	
	public void 시작(String URL, String USER, String PASSWORD) {
		사용자();
		if(디비접속()) {
			try {
				Connection conn = db.openDB(URL, USER, PASSWORD);
				if(conn != null) {
					테이블생성(conn);
					초기데이터(conn);
					boolean key = true;
					while(key) {
						System.out.println("어떤 기능을 실행하시겠습니까?(C입력/R읽기/U수정/D삭제/E종료");
						switch (scan.nextLine()) {
							case "C": 
							case "c":
								입력하기(conn);
								break;
							case "R": 
							case "r":
								가져오기(conn);
								break;
							case "U": 
							case "u":
								수정하기(conn);
								break;
							case "D": 
							case "d":
								삭제하기(conn);
								break;
							case "E": 
							case "e":
								key = false;
								break;
							default:
								System.out.println("잘못된 입력입니다. 다시 기능을 입력해주세요.");
						}
					}
					conn.close();
					종료();
				} else {
					System.out.println("데이터 접속 정보를 확인해 주세요.");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			종료();
		}
	}

}