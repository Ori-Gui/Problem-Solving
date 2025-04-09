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
            # 경로에 '/백준/' 같이 포함되어 있거나, 경로가 바로 해당 폴더일 경우
            if os.sep + cat + os.sep in root or root.endswith(os.sep + cat) or root == "./" + cat:
                main_cat = cat
                break
        if main_cat is None:
            continue
        
        # 메인 카테고리 경로 뒤의 부분을 하위 카테고리로 사용
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
            # README 파일은 제외 (각 문제 설명문으로 판단)
            if file.lower() == "readme.md":
                continue
            file_path = os.path.join(root, file)
            data[key].append(file_path)
    
    # data의 내용을 바탕으로 content 구성 (sub_cat이 (default)인 경우는 건너뜀)
    for main_cat in main_categories:
        # 메인 카테고리에 속하면서 sub_cat이 (default)가 아닌 항목들만 처리
        keys = sorted([k for k in data.keys() if k[0] == main_cat and k[1] != "(default)"],
                      key=lambda x: x[1])
        if not keys:
            continue
        # 메인 카테고리 헤더
        content += f"## 📚 {main_cat}\n"
        for key in keys:
            sub_cat = key[1]
            # 하위 카테고리 타이틀 생성
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
            # 파일 목록 추가
            for fp in sorted(data[key]):
                basename = os.path.basename(fp)
                name_no_ext = os.path.splitext(basename)[0]
                # 파일명에 언더스코어가 있으면 이를 문제번호와 제목으로 분리
                if "_" in name_no_ext:
                    parts = name_no_ext.split("_", 1)
                    prob_num = parts[0].strip()
                    prob_title = parts[1].strip()
                    display_name = f"{prob_num} - {prob_title}"
                else:
                    display_name = name_no_ext
                content += "|{}|[링크]({})|\n".format(display_name, parse.quote(fp))
    
    with open("README.md", "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
