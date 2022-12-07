using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DestroyGround : MonoBehaviour
{
    private List<GameObject> tiles = new List<GameObject>();
    //private float warningTimer = 5;
    //private bool del = false;

    private int spot;

    /*private int dir = 1;

    private float speed = 1.0f; //how fast it shakes
    private float amount = 1.0f; //how much it shakes

    private List<GameObject> tbdTiles = new List<GameObject>();*/

    public float setTime = 7.5f;
    private float timer;

    // Start is called before the first frame update
    void Start()
    {
        timer = setTime;

        foreach(GameObject x in GameObject.FindGameObjectsWithTag("Ground")){
            tiles.Add(x);
        }
    }

    // Update is called once per frame
    void Update()
    {
        if(timer >= 0){
            timer -= Time.deltaTime;
        }
        else{
            spot = Random.Range(0, tiles.Count);
            tiles[spot].GetComponent<Renderer>().material.color = new Color(255, 0, 0);

            Destroy(tiles[spot], 5);
            tiles.RemoveAt(spot);

            timer = setTime;
        }

        /*if(Input.GetKeyDown(KeyCode.F)){
            //warningTimer = 5f;
            spot = Random.Range(0, tiles.Count);
            
            //Debug.Log(tiles.Count);

            Debug.Log(tiles[spot].transform.position);

            //tiles[spot].transform.position = new Vector3(tiles[spot].transform.position.x, tiles[spot].transform.position.y + Mathf.Sin(Time.deltaTime * speed) * amount, tiles[spot].transform.position.z);

            tiles[spot].GetComponent<Renderer>().material.color = new Color(255, 0, 0);

            //tbdTiles.add(tiles[spot]);

            Destroy(tiles[spot], 5);
            tiles.RemoveAt(spot);
        }*/

        /*if(tbdTiles.length != 0){
            foreach(GameObject x in tbdTiles){
                x.transform.position = new Vector3(tiles[spot].transform.position.x, tiles[spot].transform.position.y + Mathf.Sin(Time.deltaTime * speed) * amount * dir, tiles[spot].transform.position.z);
            }
            dir *= -1;
        }*/

        /*if(warningTimer >= 0){
            warningTimer -= Time.deltaTime;
        }

        if(warningTimer >= 4){
            tiles[spot].GetComponent<Renderer>().material.color = new Color(255, 255, 0);
        }
        else if(warningTimer >= 2){
            tiles[spot].GetComponent<Renderer>().material.color = new Color(255, 87, 51);
        }
        else if(warningTimer >= 0){
            tiles[spot].GetComponent<Renderer>().material.color = new Color(255, 0, 0);
        }
        else if(warningTimer <= 0){
            Destroy(tiles[spot]);
            tiles.RemoveAt(spot);
        }*/
    }

    /*void removeTile(){
        Destroy(tiles[spot]);
        tiles.RemoveAt(spot);
    }*/
}