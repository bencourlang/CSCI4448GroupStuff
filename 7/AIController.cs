using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public class AIController : MonoBehaviour
{
    public float moveSpeed;
    //public Rigidbody body;
    public float jumpForce;

    public CharacterController controller;

    private Vector3 moveDirection;

    public float gravityScale;

    private GameObject lastJumped;

    private float lastJumpedcd = 1;


    static public float moveSpeedStatic;
    static public float jumpForceStatic;

    static public CharacterController controllerStatic;

    static private Vector3 moveDirectionStatic;

    static public float gravityScaleStatic;

    static private GameObject lastJumpedStatic;

    static private float lastJumpedcdStatic = 1;

    // Start is called before the first frame update
    void Start()
    {
        controller = GetComponent<CharacterController>();
        //tempJD = jumpDelay;
    }

    // Update is called once per frame
    void Update()
    {
        if(lastJumpedcd >= 0){
            lastJumpedcd -= Time.deltaTime;
        }
        else{
            lastJumped = null;
        }

        /*GameObject temp = GetClosestObject("Laser");
        if(temp != null){
            print("Closest Object is " + temp.name);
        }*/

        float yStore = moveDirection.y;
        //moveDirection = (transform.forward * Input.GetAxis("Vertical") * moveSpeed) + (transform.right * Input.GetAxis("Horizontal") * moveSpeed);
        moveDirection = moveDirection.normalized * moveSpeed;
        moveDirection.y = yStore;

        if(controller.isGrounded){
            moveDirection.y = -3f;

            if(aiJump("Laser")){
                moveDirection.y = jumpForce;
            }
        }

        /*if(tempJD >= 0){
            tempJD -= Time.deltaTime;
        }*/

        moveDirection.y += (Physics.gravity.y * Time.deltaTime * gravityScale);
        controller.Move(moveDirection * Time.deltaTime);
    }

    /*GameObject GetClosestObject(string tag){
        var objectsWithTag = GameObject.FindGameObjectsWithTag(tag);
        GameObject closestObject = null;

        foreach(GameObject obj in objectsWithTag){
        //for (var obj : GameObject in objectsWithTag){
            if(!closestObject){
                closestObject = obj;
            }
            //compares distances
            if(Vector3.Distance(transform.position, obj.transform.position) <= Vector3.Distance(transform.position, closestObject.transform.position)){
                closestObject = obj;
            }
        }
        return closestObject;
    }*/

    bool aiJump(string tag){
        var objectsWithTag = GameObject.FindGameObjectsWithTag(tag);

        foreach(GameObject obj in objectsWithTag){
            if(Vector3.Distance(transform.position, obj.transform.position) <= 3 && obj != lastJumped){
                lastJumped = obj;
                return true;
            }
        }
        return false;
    }

    public abstract class Strategy
    {
        public abstract void PlayerOrAI();
    }

    public class ConcreteStrategyAI : Strategy
    {
        public override void PlayerOrAI()
        {
            AIControl();
        }
    }

    public class ConcreteStrategyPlayer : Strategy
    {
        public override void PlayerOrAI()
        {
            PlayerControl();
        }
    }

    public class Context
    {
        Strategy strategy;
        // Constructor
        public Context(Strategy strategy)
        {
            this.strategy = strategy;
        }
        public void ContextInterface()
        {
            strategy.PlayerOrAI();
        }
    }

    static public void AIControl(){
        if(lastJumpedcdStatic >= 0){
            lastJumpedcdStatic -= Time.deltaTime;
        }
        else{
            lastJumpedStatic = null;
        }

        float yStore = moveDirectionStatic.y;
        moveDirectionStatic = moveDirectionStatic.normalized * moveSpeedStatic;
        moveDirectionStatic.y = yStore;

        if(controllerStatic.isGrounded){
            moveDirectionStatic.y = -3f;

            /*if(aiJump("Laser")){
                moveDirectionStatic.y = jumpForceStatic;
            }*/
        }

        moveDirectionStatic.y += (Physics.gravity.y * Time.deltaTime * gravityScaleStatic);
        controllerStatic.Move(moveDirectionStatic * Time.deltaTime);
    }

    static public void PlayerControl(){
        if(lastJumpedcdStatic >= 0){
            lastJumpedcdStatic -= Time.deltaTime;
        }
        else{
            lastJumpedStatic = null;
        }

        float yStore = moveDirectionStatic.y;
        moveDirectionStatic = moveDirectionStatic.normalized * moveSpeedStatic;
        moveDirectionStatic.y = yStore;

        if(controllerStatic.isGrounded){
            moveDirectionStatic.y = -3f;

            /*if(aiJump("Laser")){
                moveDirectionStatic.y = jumpForceStatic;
            }*/
        }

        moveDirectionStatic.y += (Physics.gravity.y * Time.deltaTime * gravityScaleStatic);
        controllerStatic.Move(moveDirectionStatic * Time.deltaTime);
    }
}
