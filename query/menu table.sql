-- watchdog.receive_condition definition

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `menu_name` varchar(255) NOT NULL COMMENT '메뉴명',
  `path` varchar(255) NOT NULL COMMENT 'link-url',
  `depth` int NOT NULL COMMENT 'depth',
  `order` int NOT NULL COMMENT '정렬순서',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '사용여부',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '수정일시',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메뉴 테이블';


select * from menu;

commit;

INSERT INTO watchdog.menu
(ID, menu_name, `path`, `depth`, `order`, status, created_at, updated_at)
VALUES(1000,'전체 감시', '/', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO watchdog.menu
(ID, menu_name, `path`, `depth`, `order`, status, created_at, updated_at)
VALUES(2000, '지점별 감시', '#', 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO watchdog.menu
(ID, menu_name, `path`, `depth`, `order`, status, created_at, updated_at)
VALUES(3000, '과거자료 검색', '#', 1, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO watchdog.menu
(ID, menu_name, `path`, `depth`, `order`, status, created_at, updated_at)
VALUES(4000, '지점별 통계', '#', 1, 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO watchdog.menu
(ID, menu_name, `path`, `depth`, `order`, status, created_at, updated_at)
VALUES(5000, '문자관리', '#', 1, 5, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO watchdog.menu
(ID, menu_name, `path`, `depth`, `order`, status, created_at, updated_at)
VALUES(6000, '사용자 관리', '/users/admin_user', 1, 6, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);






private String menu_name;
	private String path; 
	private Boolean status; 
	private int depth;
	private String order; 
	private Boolean created_at;
	private String updated_at;


-- im.menu definition

CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '일련번호',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '생성일시',
  `created_by` varchar(40) NOT NULL DEFAULT 'admin@ldcc.kr' COMMENT '생성자',
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '수정일시',
  `updated_by` varchar(40) NOT NULL DEFAULT 'admin@ldcc.kr' COMMENT '수정자',
  `use_flag` tinyint(1) NOT NULL DEFAULT 1 COMMENT '사용여부',
  `korean_name` varchar(255) NOT NULL COMMENT '한글명',
  `english_name` varchar(255) NOT NULL COMMENT '영문명',
  `url` varchar(255) NOT NULL COMMENT 'link-url',
  `parent_menu_id` bigint(20) NOT NULL COMMENT '상위메뉴id',
  `title` varchar(255) NOT NULL COMMENT '타이틀',
  `catalogue_id` int(11) NOT NULL DEFAULT 0 COMMENT '상품 연계를 위한 카탈로그ID',
  `sort_no` int(11) NOT NULL DEFAULT 0 COMMENT '정렬순서',
  `mini` varchar(30) DEFAULT NULL COMMENT '메뉴 텍스트 아이콘',
  `url_type` varchar(10) NOT NULL COMMENT 'url 타입 : PAGE/POPUP',
  `console_display_flag` tinyint(1) NOT NULL DEFAULT 1 COMMENT '노출 여부',
  `protected_flag` tinyint(1) NOT NULL DEFAULT 1 COMMENT '보호 여부 (수정 및 삭제 불가)',
  `guide_description` varchar(500) DEFAULT NULL COMMENT '안내문구',
  `guide_description_flag` tinyint(1) NOT NULL DEFAULT 1 COMMENT '안내문구 사용여부',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='사용자포탈_메뉴';