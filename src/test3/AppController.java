package test3;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		sql = "create table tb1 (no int, email varchar(100), password varchar(60), delYn boolean )"; // DDL 추가
		System.out.println("테이블 생성 SQL문 확인 : " + sql);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.		
		db.테이블생성(conn, sql);
	}
	
	// 2단계 생성된 테이블에 데이터 입력하기
	private void 입력하기(Connection conn, DbTable data) {
		sql = "insert into tb1 value (?, ?, ?, ?)"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
//		DbTable data = new DbTable();
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터입력하기(conn, sql, data);
	}
	
	private void 초기데이터생성(Connection conn) {
		DbTable data = new DbTable();
		
//		List list = new ArrayList();
		data.setNo(1);
		data.setEmail("홍길동@이메일.컴");
		data.setPassword("pAssWorD");
		data.setDelYn(false);
//		list.add(data);
		입력하기(conn, data);
		
		
		data.setNo(2);
		data.setEmail("유관순@이메일.컴");
		data.setPassword("1@34%");
		data.setDelYn(true);
//		list.add(data);
		입력하기(conn, data);
		
		data.setNo(3);
		data.setEmail("이순신@이메일.컴");
		data.setPassword("!!9!2");
		data.setDelYn(true);
//		list.add(data);
		입력하기(conn, data);
		
		data.setNo(4);
		data.setEmail("화이슬@이메일.컴");
		data.setPassword("ghkdltmf");
		data.setDelYn(false);
//		list.add(data);
		입력하기(conn, data);
		
//		return list;
	}
	
	// 3단계 생성된 데이터 가져오기 >> 목록 출력 시 화면출력() 메소드를 사용하시오.
	private void 가져오기(Connection conn) {
		sql = "select * from tb1"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		화면출력(db.데이터가져오기(conn, sql));
	}
	
	// 4단계 생성된 데이터 수정하기
	private void 수정하기(Connection conn) {
		sql = "update tb1 set password = ?, email = ? where no = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();
		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		data.setNo(4);
		data.setEmail("신촌맨@이메일.컴");
		data.setPassword("tlschsaos");
		
		System.out.println(data);
		// 코드추가를 확인 후 아래의 주석을 풀고 실행하세요.
		db.데이터수정하기(conn, sql, data);
	}
	
	// 5단계 생성된 데이터 삭제하기
	private void 삭제하기(Connection conn) {
		sql = "update tb1 set delYn = 1 where no = ?"; // DML 추가
		System.out.println("SQL문 확인 : " + sql);
		DbTable data = new DbTable();

		/*****************************************
		 *  Scanner 사용시 추가
		 *****************************************/
		data.setNo(5);
		
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
					//테이블생성(conn);
					//초기데이터생성(conn);
					boolean key = true;
					while(key) {
						System.out.println("어떤 기능을 실행하시겠습니까?(C입력/R읽기/U수정/D삭제/E종료");
						switch (scan.nextLine()) {
							case "C": 
							case "c":
								// 입력하기 메소드 재사용으로 변경 했을 경우
								DbTable data = new DbTable();
								data.setNo(5);
								data.setEmail("하이디@이메일.컴");
								data.setPassword("hiDe");
								data.setDelYn(false);
								입력하기(conn, data);
//								입력하기(conn);
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