-- ==========================================
-- IPL Management System - PL/SQL Components
-- ==========================================

-------------------------------------------------
-- Procedure : Match Summary
-------------------------------------------------

CREATE OR REPLACE PROCEDURE match_summary_proc (
    m_id NUMBER,
    team1 OUT VARCHAR2,
    team2 OUT VARCHAR2,
    score1 OUT NUMBER,
    score2 OUT NUMBER,
    result OUT VARCHAR2
) AS
BEGIN
    SELECT t1.team_name,
           t2.team_name,
           m.team1_score,
           m.team2_score
    INTO team1,
         team2,
         score1,
         score2
    FROM match m
    JOIN team t1 ON m.team1_id = t1.team_id
    JOIN team t2 ON m.team2_id = t2.team_id
    WHERE m.match_id = m_id;

    IF score1 > score2 THEN
        result := team1 || ' won by ' || (score1 - score2) || ' runs';
    ELSIF score2 > score1 THEN
        result := team2 || ' won by ' || (score2 - score1) || ' runs';
    ELSE
        result := 'Match Draw';
    END IF;
END;
/

-------------------------------------------------
-- Function : Strike Rate
-------------------------------------------------

CREATE OR REPLACE FUNCTION get_strike_rate(
    p_name VARCHAR2
)
RETURN NUMBER
IS
    v_runs NUMBER;
    v_balls NUMBER;
    v_sr NUMBER;
BEGIN
    SELECT ps.runs_scored,
           ps.balls_faced
    INTO v_runs,
         v_balls
    FROM player p
    JOIN player_stats ps
    ON p.player_id = ps.player_id
    WHERE p.name = p_name;

    IF v_balls = 0 THEN
        RETURN 0;
    END IF;

    v_sr := (v_runs / v_balls) * 100;

    RETURN v_sr;
END;
/

-------------------------------------------------
-- Cursor Procedure : Players By Team
-------------------------------------------------

CREATE OR REPLACE PROCEDURE players_by_team(
    t_id NUMBER
)
IS
    CURSOR c_players IS
        SELECT name, role
        FROM player
        WHERE team_id = t_id;

    v_name player.name%TYPE;
    v_role player.role%TYPE;

BEGIN
    OPEN c_players;

    LOOP
        FETCH c_players INTO v_name, v_role;
        EXIT WHEN c_players%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(v_name || ' - ' || v_role);
    END LOOP;

    CLOSE c_players;
END;
/

-------------------------------------------------
-- Trigger : Validate Stadium Data
-------------------------------------------------

CREATE OR REPLACE TRIGGER validate_stadium
BEFORE INSERT ON stadium
FOR EACH ROW
BEGIN
    IF :NEW.capacity <= 0 THEN
        RAISE_APPLICATION_ERROR(
            -20001,
            'Stadium capacity must be greater than zero.'
        );
    END IF;

    IF :NEW.stadium_name IS NULL THEN
        RAISE_APPLICATION_ERROR(
            -20002,
            'Stadium name cannot be NULL.'
        );
    END IF;

    IF :NEW.city IS NULL THEN
        RAISE_APPLICATION_ERROR(
            -20003,
            'City cannot be NULL.'
        );
    END IF;
END;
/