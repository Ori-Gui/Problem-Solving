#!/usr/bin/env python

import os
from urllib import parse

HEADER = """#
# 🎯 백준, 프로그래머스, SWEA 문제 풀이 목록
"""

# 백준 등급 이모지 매핑 (예시)
BOJ_TIER_ORDER = {
    "Bronze": "🥉 Bronze",
    "Silver": "🥈 Silver",
    "Gold": "🥇 Gold",
    "Platinum": "💚 Platinum",
    "Diamond": "❤ Diamond",
    "Ruby": "❤️ Ruby"
}

# 프로그래머스 레벨 이모지 매핑 (예시)
PROGRAMMERS_LEVEL = {
    "0": "🍼 Lv.0",
    "1": "🐣 Lv.1",
    "2": "🐥 Lv.2",
    "3": "🐤 Lv.3",
    "4": "🦉 Lv.4",
    "5": "🦅 Lv.5"
}

# SWEA 단계 이모지 매핑 (예시)
def swea_label(name):
    return f"🌟 {name.upper()}"

def parse_problem_folder(folder_name: str) -> str:
    """
    '1000. A+B' 같은 폴더 이름을 '1000 - A+B' 로 변환.
    폴더명에 '.'이 없으면 그대로 반환.
    """
    if "." in folder_name:
        parts = folder_name.split(".", 1)
        prob_num = parts[0].strip()
        prob_title = parts[1].strip()
        return f"{prob_num} - {prob_title}"
    else:
        return folder_name

def main():
    content = HEADER + "\n"
    
    # 메인 카테고리 이름들
    main_categories = ["백준", "프로그래머스", "SWEA"]
    # 카테고리별 파일 정보를 저장할 딕셔너리
    # 키: (메인카테고리, 하위카테고리) / 하위카테고리가 폴더명이 되며, 파싱을 통해 표시
    data = {}
    
    for root, dirs, files in os.walk("."):
        # .git, .github, images 폴더는 건너뛰기
        dirs[:] = [d for d in dirs if d not in (".git", ".github", "images")]
        if root == ".":
            continue
        
        # 이 경로(root)에서 메인 카테고리가 어느 것에 속하는지 체크
        main_cat = None
        for cat in main_categories:
            # 경로에 '/백준/' 같이 포함되어 있거나, 경로가 바로 해당 폴더인 경우
            if os.sep + cat + os.sep in root or root.endswith(os.sep + cat) or root == "./" + cat:
                main_cat = cat
                break
        if main_cat is None:
            continue
        
        # 메인 카테고리 바로 아래 폴더(또는 그 아래 하위 경로)를 sub_cat으로 간주
        main_path = os.path.join(".", main_cat)
        rel_path = os.path.relpath(root, main_path)  # 예: 'Bronze/1000. A+B' 등
        # 하위 경로를 '/'로 나눈 첫 요소가 하위 카테고리(예: Bronze)
        parts = rel_path.split(os.sep)
        sub_cat = parts[0]  # 예: Bronze
        
        # 만약 하위 카테고리도 또 폴더를 갖고 있다면?
        # (1000. A+B 같은 폴더는 'Bronze/1000. A+B'에서 두 번째 요소)
        # 예) rel_path = "Bronze/1000. A+B" 라면
        # 하위 디렉토리명은 '1000. A+B' (여기서 문제번호/문제이름 파싱)
        problem_folder = None
        if len(parts) > 1:
            problem_folder = parts[1]  # "1000. A+B" 같은 실제 문제 폴더
        
        # 데이터 구조: data[(main_cat, sub_cat)] = { 문제폴더이름: [파일목록...] }
        # 없으면 초기화
        if (main_cat, sub_cat) not in data:
            data[(main_cat, sub_cat)] = {}
        
        if problem_folder is not None:
            if problem_folder not in data[(main_cat, sub_cat)]:
                data[(main_cat, sub_cat)][problem_folder] = []
            
            for file in files:
                if file.lower() == "readme.md":  # 문제 설명 파일 제외
                    continue
                full_path = os.path.join(root, file)
                data[(main_cat, sub_cat)][problem_folder].append(full_path)
        else:
            # 문제폴더가 아니라, sub_cat 자신이 문제폴더인 경우도 있을 수 있으나
            # (ex: 백준/Lv.0 에 파일이 바로 있는 경우)
            # 여기서는 별도로 처리할지 여부 결정 (원한다면 default 처리)
            pass
    
    # data의 내용을 바탕으로 content 구성
    for main_cat in main_categories:
        # 해당 메인 카테고리에 대응하는 (main_cat, sub_cat) 목록
        keys = [(k, v) for k, v in data.items() if k[0] == main_cat]
        if not keys:
            continue
        
        # 메인 카테고리 헤더
        content += f"## 📚 {main_cat}\n"
        
        # sub_cat(예: Bronze, Silver 등) 알파벳/한글 순으로 정렬
        keys_sorted = sorted(keys, key=lambda x: x[0][1])
        
        for (mc, sub_cat), problem_map in keys_sorted:
            # 백준, 프로그래머스, SWEA 각각 서브카테고리를 어떻게 표현할지
            if mc == "백준":
                tier_title = BOJ_TIER_ORDER.get(sub_cat, f"✅ {sub_cat}")
            elif mc == "프로그래머스":
                tier_title = PROGRAMMERS_LEVEL.get(sub_cat, f"📘 Lv.{sub_cat}")
            elif mc == "SWEA":
                tier_title = swea_label(sub_cat)
            else:
                tier_title = sub_cat
            
            content += f"### {tier_title}\n"
            
            # 문제 폴더들을 표시
            # 문제 폴더명 예: "1000. A+B"
            #    → parse_problem_folder( "1000. A+B" ) = "1000 - A+B"
            
            # 문제별 마크다운 테이블 (문제폴더와 해당 폴더 안의 파일목록)
            for pfolder, file_list in sorted(problem_map.items()):
                parsed_name = parse_problem_folder(pfolder)
                # 문제 테이블의 헤더
                content += f"#### {parsed_name}\n"
                content += "| 파일명(소스) | 링크 |\n"
                content += "| ---------- | ---- |\n"
                for fp in sorted(file_list):
                    file_name = os.path.basename(fp)
                    # 소스코드 파일명 그대로 표시
                    content += f"|{file_name}|[링크]({parse.quote(fp)})|\n"
                content += "\n"
    
    with open("README.md", "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
