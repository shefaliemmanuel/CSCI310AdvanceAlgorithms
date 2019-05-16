//  main.c
//  CSCI310Midterm
//  Created by Shefali Emmanuel on 11/17/18.
//  Copyright © 2018 Shefali Emmanuel. All rights reserved.

# include <limits.h>
# include <string.h>
# include <stdio.h>

// preprocessing for strong good suffix rule
void patternShifting(int *shift, int *borderPosition, char *patternToMatch, int lengthOfPattern){
    int i = lengthOfPattern;
    int j = lengthOfPattern + 1;
    borderPosition[i]= j;
    
    while(i>0){
                                    //if character at position i-1 is not equivalent to character at j-1, then keep going to the right
        while(j<=lengthOfPattern && patternToMatch[i-1] != patternToMatch[j-1]){
            //Border = a substring which is both proper suffix and proper prefix
            //Border width will be smaller than the border starting at position j ie. smaller than x
            //or
            //Border could be empty
            if (shift[j]==0){ //if the character is different then we shift
                shift[j] = j-i; //shift from i to j
            }
            //Update the position of next border
            j = borderPosition[j];
        }
        
        //we decrememnt these variables to move backwards in the list
        i--;
        j--;
        
        //now we compare character at position j-1 and i-1 to do another check
        borderPosition[i] = j; //if this works= new border
                               // else continue
    }
}


void suffixShifts(int *shift, int *borderPosition,char *patternToMatch, int lengthOfPattern){
    int i, j;
    j = borderPosition[0]; //widest border of the pattern
    for(i=0; i<=lengthOfPattern; i++){
        //borderPosition of the 1st character of the pattern
        if(shift[i]==0){
            shift[i] = j;
        }
        if (i==j){ //if the suffix of the pattern becomes shorter than borderPosition[0]
            j = borderPosition[j]; //then it finds the next largest border, this is its way or working backwards from largest to smallest
        }
    }
}


void search(char *text, char *patternToMatch){
    int s = 0; //amount to shift
    int j;
    int lengthOfPattern = strlen(patternToMatch);
    int n = strlen(text);
    int borderPosition[lengthOfPattern+1];
    int shift[lengthOfPattern+1];
    
    //initialize all occurence of shift to 0
    for(int i=0;i<lengthOfPattern+1;i++){
        shift[i]=0;
    }
    
    patternShifting(shift, borderPosition, patternToMatch, lengthOfPattern);
    suffixShifts(shift, borderPosition, patternToMatch, lengthOfPattern);
    
    while(s <= n-lengthOfPattern){
        j = lengthOfPattern-1;
        
        //decrement j while matching and shifting is happening
        while(j >= 0 && patternToMatch[j] == text[s+j]){
            j--;
        }
        //if pattern = current shift
            //then j = -1
        if (j<0){
            printf("Pattern occurs at position = %d\n", s);
            s += shift[0]; //shift nothing because it matched
        }else{
            //if the pattern does not match
            printf("Pattern before shifting at = %d\n", s);
            s += shift[j+1]; //keep incrementing by 1
            printf("Pattern after shifting at = %d\n", s);
        }
    }
}

int main(){
    char textPhrase[] = "SHEFSH3FALISHEFAILSHEFAIILSHAFALI";
    printf("Text:    %s\n",textPhrase);
    char patternToMatch[] = "AIL";
    printf("Pattern: %s\n",patternToMatch);
    printf("\n");
    search(textPhrase,patternToMatch);
    printf("\n");
    return 0;
}
