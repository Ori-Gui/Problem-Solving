#!/usr/bin/env python

import os
from urllib import parse

HEADER = """#
# 🎯 백준, 프로그래머스, SWEA 문제 풀이 목록
"""

# 백준 등급 이모지 매핑
BOJ_TIER_ORDER = {
    "Bronze": "🥉 Bronze",
    "Silver": "🥈 Silver",
    "Gold": "🥇 Gold",
    "Platinum": "💚 Platinum",
    "Diamond": "❤ Diamond",
    "Ruby": "❤️ Ruby"
}

# 프로그래머스 레벨 이모지 매핑
PROGRAMMERS_LEVEL = {
    "0": "🍼 Lv.0",
    "1": "🐣 Lv.1",
    "2": "🐥 Lv.2",
    "3": "🐤 Lv.3",
    "4": "🦉 Lv.4",
    "5": "🦅 Lv.5"
}

# SWEA 단계 이모지 매핑
def swea_label(name):
    return f"🌟 {name.upper()}"

def main():
    content = ""
    content += HEADER + "\n"
    
    # 메인 카테고리 이름들
    main_categories = ["백준", "프로그래머스", "SWEA"]
    # 카테고리별 파일 정보를 저장할 딕셔너리
    # 키: (메인카테고리, 하위카테고리) / 하위카테고리 없으면 "(default)" 사용
    data = {}
    
    for root, dirs, files in os.walk("."):
        # .git, .github, images 폴더는 건너뛰기
        dirs[:] = [d for d in dirs if d not in (".git", ".github", "images")]
        if root == ".":
            continue
        
        # 이 경로(root)에서 메인 카테고리가 어느 것에 속하는지 체크
        main_cat = None
        for cat in main_categories:
            # 경로에 '/백준/' 같이 포함되어 있는지 또는 경로가 바로 해당 폴더인지 확인
            if os.sep + cat + os.sep in root or root.endswith(os.sep + cat) or root == "./" + cat:
                main_cat = cat
                break
        if main_cat is None:
            continue
        
        # 메인 카테고리 경로 뒤에 오는 부분을 하위 카테고리로 사용한다.
        main_path = os.path.join(".", main_cat)
        rel_path = os.path.relpath(root, main_path)
        if rel_path == ".":  # 메인 카테고리 폴더 자체라면
            sub_cat = "(default)"
        else:
            sub_cat = rel_path.split(os.sep)[0]  # 바로 아래 폴더명
        
        key = (main_cat, sub_cat)
        if key not in data:
            data[key] = []
        
        for file in files:
            file_path = os.path.join(root, file)
            data[key].append(file_path)
    
    # data 내용을 바탕으로 content 구성
    for main_cat in main_categories:
        # 해당 메인 카테고리에 관련된 키들을 가져옴
        keys = sorted([k for k in data.keys() if k[0] == main_cat], key=lambda x: x[1])
        if not keys:
            continue
        # 메인 카테고리 헤더
        content += f"## 📚 {main_cat}\n"
        for key in keys:
            sub_cat = key[1]
            if sub_cat == "(default)":
                title = sub_cat
            else:
                if main_cat == "백준":
                    title = BOJ_TIER_ORDER.get(sub_cat, f"✅ {sub_cat}")
                elif main_cat == "프로그래머스":
                    title = PROGRAMMERS_LEVEL.get(sub_cat, f"📘 Lv.{sub_cat}")
                elif main_cat == "SWEA":
                    title = swea_label(sub_cat)
                else:
                    title = sub_cat
            # 하위 카테고리 헤더 및 테이블 헤더
            content += f"### {title}\n"
            content += "| 문제번호 & 문제명 | 링크 |\n"
            content += "| ----- | ----- |\n"
            # 파일 목록 추가 (문제번호는 파일명에서 확장자 제거한 값)
            for fp in sorted(data[key]):
                problem_id = os.path.splitext(os.path.basename(fp))[0]
                content += "|{}|[링크]({})|\n".format(problem_id, parse.quote(fp))
    
    with open("README.md", "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
