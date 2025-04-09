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
    return f"🌟 {name.upper()}"  # e.g., D1 → 🌟 D1

def main():
    content = ""
    content += HEADER
    
    written_titles = set()
    written_main_dirs = set()

    for root, dirs, files in os.walk("."):
        dirs.sort()
        if root == '.':
            for dir in ('.git', '.github'):
                try:
                    dirs.remove(dir)
                except ValueError:
                    pass
            continue

        category = os.path.basename(root)
        parent_dir = os.path.basename(os.path.dirname(root))

        if category == 'images':
            continue

        # 상위 분류 헤더 출력 (백준, 프로그래머스, SWEA)
        if parent_dir in ["백준", "프로그래머스", "SWEA"]:
            if parent_dir not in written_main_dirs:
                content += f"## 📚 {parent_dir}\n"
                written_main_dirs.add(parent_dir)

            # 세부 카테고리별 타이틀 생성
            if parent_dir == "백준":
                tier_title = BOJ_TIER_ORDER.get(category, f"✅ {category}")
            elif parent_dir == "프로그래머스":
                tier_title = PROGRAMMERS_LEVEL.get(category, f"📘 Lv.{category}")
            elif parent_dir == "SWEA":
                tier_title = swea_label(category)
            else:
                tier_title = f"☑️ {category}"

            title_key = f"{parent_dir}/{category}"
            if title_key not in written_titles:
                content += f"### {tier_title}\n"
                content += "| 문제번호 & 문제명 | 링크 |\n"
                content += "| ----- | ----- |\n"
                written_titles.add(title_key)
        else:
            continue  # 무시할 디렉토리

        # 폴더 내 모든 파일을 출력 (각 파일을 문제로 간주)
        for file in files:
            problem_id = os.path.splitext(file)[0]
            file_path = os.path.join(root, file)
            content += "|{}|[링크]({})|\n".format(problem_id, parse.quote(file_path))

    with open("README.md", "w", encoding="utf-8") as fd:
        fd.write(content)

if __name__ == "__main__":
    main()
