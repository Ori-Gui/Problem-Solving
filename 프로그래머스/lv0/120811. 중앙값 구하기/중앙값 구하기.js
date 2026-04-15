function solution(array) {
    let arr = array.sort();
    let answer = arr[Math.floor(arr.length/2)];
    return answer;
}