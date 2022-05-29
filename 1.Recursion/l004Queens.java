public class l004Queens{

    //question 1 
    public static int queenCombination1D(int queenNo, int totalQueens,int BoxId,boolean[] boxes,String psf){
        if(BoxId == boxes.length || queenNo == totalQueens){
            if(queenNo == totalQueens){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for(int i = BoxId; i<boxes.length;i++){
            count += queenCombination1D(queenNo+1, totalQueens,i+1, boxes,psf + " q" + queenNo+"b"+i);
        }
        return count;
    }
    public static int queenCombination1D_SS(int queenNo, int totalQueens,int BoxId,int totalBoxes,String psf){
        if(BoxId == totalBoxes || queenNo == totalQueens){
            if(queenNo == totalQueens){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        //queen lene ki call b0 pe
        int count = 0;
        count += queenCombination1D_SS(queenNo+1, totalQueens, BoxId+1, totalBoxes, psf+" q"+queenNo + "b"+BoxId);
        //queen na lene ki call
        count += queenCombination1D_SS(queenNo, totalQueens, BoxId+1, totalBoxes, psf);
        return count;
    }

    //=============================================================================================================================

    //q2
    public static int queenPermutation1D(int qIdx,int totalQueens,boolean[] boxes,String psf){
        if(qIdx == totalQueens){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int i = 0; i<boxes.length;i++){
            if(boxes[i] != true){
                boxes[i] = true;
                count += queenPermutation1D(qIdx+1,totalQueens,boxes,psf+" q"+qIdx+"b"+i);
                boxes[i] = false;
            }
        }
        return count;
    }

    public static int queenPermutation1D_SS(int qIdx,int totalQueens,int boxId,boolean[] boxes,String psf){
        if(boxId == boxes.length || qIdx == totalQueens){
            if(qIdx == totalQueens){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        //queen lene ki call
        if(boxes[boxId] == false){
            boxes[boxId] = true;
            count+=queenPermutation1D_SS(qIdx+1, totalQueens,0, boxes, psf+" q"+qIdx+"b"+boxId);
            boxes[boxId] = false;
        }
        //queen na lene ki call
        count+=queenPermutation1D_SS(qIdx, totalQueens,boxId+1, boxes, psf);
        return count;
    }

    //=============================================================================================================================

    //question 3 nxn mai n queens place karro 
    public static int queenCombination2D(int queenIdx,int boxNo, boolean[][] boxes,String psf){
        int n = boxes.length;
        if(boxNo == n*n || queenIdx == n){
            if(queenIdx == n){
                System.out.println(psf);
                return 1;
            }
            return 0;
        }
        int count = 0;
        for(int i = boxNo; i<n*n; i++){
            int r = i/n, c = i%n;
            count += queenCombination2D(queenIdx+1, i+1,boxes, psf+"("+r+","+c+")");
        }
        return count;
    }

    //=============================================================================================================================

    //question 4 
    public static int queenPermutation2D(int queenIdx, boolean[][] boxes,String psf){
        int n = boxes.length;
        if(queenIdx == n){
            System.out.println(psf);
            return 1;
        }
           
        int count = 0;
        for(int i = 0; i<n*n; i++){
            int r = i/n, c = i%n;
            if(boxes[r][c] == false){
                boxes[r][c] = true;
                count += queenPermutation2D(queenIdx+1,boxes, psf+"("+r+","+c+")");
                boxes[r][c] = false;
            }
        }
        return count;

    }
    //==============================================================================================================================
    //question 5 :- N queens leetcode wwala
    public static int queenCombinationLeetcode(int qIdx,int boxNo, boolean[][] board, String psf){
        int n = board.length;
        if(qIdx == 0){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int i = boxNo; i<n*n; i++){
            int r = i/n, c = i%n;
            if(isSafeToPlaceQueen(r,c,4,board) == true){//4 represent first 4 direction to check
                board[r][c] = true;
                count += queenCombinationLeetcode(qIdx-1, i+1, board, psf+"("+r+","+c+")");
                board[r][c] = false;
            }
        }
        return count;
    }
    //helper function for queenCombinationLeetcode
    public static boolean isSafeToPlaceQueen(int r,int c,int directionToCheck ,boolean[][] board){
        int[][] dir = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
        for(int i = 0; i<directionToCheck; i++){
            for(int radius = 1; radius<=board.length; radius++){
                int x = r + radius*dir[i][0];
                int y = c + radius*dir[i][1];
                if(x>=0 && y>=0 && x<board.length && y<board[0].length){
                    if(board[x][y] == true) return false;
                }else{
                    break;
                }
            }
        }
        return true;
    }
    //    . . Q . 
    //    Q . . . 
    //    . . . Q
    //    . Q . .
    
    //=============================================================================================================================
    //question 6 : N queen leetcode but print permutations
    public static int queenPermutationLeetcode(int qIdx, boolean[][] board, String psf){
        int n = board.length;
        if(qIdx == 0){
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for(int i = 0; i<n*n; i++){
            int r = i/n, c = i%n;
            if(board[r][c] == false && isSafeToPlaceQueen(r,c,8,board) == true){//8 represent first 8 direction to check
                board[r][c] = true;
                count += queenPermutationLeetcode(qIdx-1, board, psf+"("+r+","+c+")");
                board[r][c] = false;
            }
        }
        return count;
    }
    //for 4x4 total combination to place n Q was 2 ab perm. hai to 2*4! (4! to arrange 4 queens) 2*4! => 2*24 =  48 total permutation
    //=========================================================================================================================================
 
    public static void main(String[] args) {
        // System.out.println("sahib");
        boolean[][] boxes = new boolean[4][4];
        // System.out.println(queenCombination1D(0,3,0,boxes,""));
        // System.out.println(queenCombination1D_SS(0,3,0,5,""));
        // System.out.println(queenPermutation1D(0, 3, boxes,""));
        // System.out.println(queenPermutation1D_SS(0, 3,0, boxes,""));
        // System.out.println(queenCombination2D(0, 0, boxes, ""));
        // System.out.println(queenPermutation2D(0, boxes, ""));
        // System.out.println(queenCombinationLeetcode(4,0,boxes,""));
        // System.out.println(queenPermutationLeetcode(4,boxes,""));
        
    }
}