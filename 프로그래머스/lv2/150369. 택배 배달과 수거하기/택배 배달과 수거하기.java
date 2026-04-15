import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int dIdx = n - 1; // deliveries의 마지막 인덱스(가장 먼 집)
        int pIdx = n - 1; // pickups의 마지막 인덱스(가장 먼 집)
        
        // 아직 배달 또는 수거할 집이 남아있는 동안 반복
        while(dIdx >= 0 || pIdx >= 0) {
            // 아직 배달할 집이 남아있다면, 0인 집은 건너뛰기
            while(dIdx >= 0 && deliveries[dIdx] == 0) {
                dIdx--;
            }
            // 아직 수거할 집이 남아있다면, 0인 집은 건너뛰기
            while(pIdx >= 0 && pickups[pIdx] == 0) {
                pIdx--;
            }
            
            // 배달, 수거 완료
            if(dIdx < 0 && pIdx < 0) {
                break;
            }
            
            // 왕복으로 처리할 최대 거리
            int dist = Math.max(dIdx, pIdx) + 1;
            answer += (long) dist * 2;  // 왕복 이동 거리 누적
            
            int deliverCap = cap;
            int pickupCap = cap;
            
            // 배달 처리: 가장 먼 집부터 cap만큼 배달을 처리
            while(dIdx >= 0 && deliverCap > 0) {
                if(deliveries[dIdx] <= deliverCap) {
                    deliverCap -= deliveries[dIdx];
                    deliveries[dIdx] = 0;
                    dIdx--;
                } else {
                    deliveries[dIdx] -= deliverCap;
                    deliverCap = 0;
                }
            }
            
            // 수거 처리: 가장 먼 집부터 cap만큼 수거를 처리
            while(pIdx >= 0 && pickupCap > 0) {
                if(pickups[pIdx] <= pickupCap) {
                    pickupCap -= pickups[pIdx];
                    pickups[pIdx] = 0;
                    pIdx--;
                } else {
                    pickups[pIdx] -= pickupCap;
                    pickupCap = 0;
                }
            }
        }
        
        return answer;
    }
}
