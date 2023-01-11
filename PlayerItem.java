public class PlayerItem {
    public People player1;
    public People player2;
    public Integer winner;

    public PlayerItem(People player1, People player2, Integer winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public People getPlayer1() {
        return player1;
    }

    public void setPlayer1(People player1) {
        this.player1 = player1;
    }

    public People getPlayer2() {
        return player2;
    }

    public void setPlayer2(People player2) {
        this.player2 = player2;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "\n\nПара{" + player1 + " VS" + player2 +
                ", \n \tПобедитель №" + winner +
                '}';
    }
}
